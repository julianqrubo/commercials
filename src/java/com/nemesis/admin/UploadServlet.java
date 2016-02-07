/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jolbox.bonecp.BoneCP;
import com.nemesis.admin.utils.Constants;
import com.nemesis.entity.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.imgscalr.Scalr;

/**
 *
 * @author mcasallas
 */
@WebServlet(name = "UploadServlet", urlPatterns = "/admin/upload")
public class UploadServlet extends HttpServlet {

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
            File file = getFile(request, request.getParameter("getfile"));
            if (file.exists()) {
                int bytes = 0;
                try (ServletOutputStream op = response.getOutputStream()) {
                    response.setContentType(getMimeType(file));
                    response.setContentLength((int) file.length());
                    response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

                    byte[] bbuf = new byte[1024];
                    DataInputStream in = new DataInputStream(new FileInputStream(file));

                    while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                        op.write(bbuf, 0, bytes);
                    }

                    in.close();
                    op.flush();
                }
            }
        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
            File file = getFile(request, request.getParameter("delfile"));
            if (file.exists()) {
                file.delete(); // TODO:check and report success
            }
        } else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
            File file = getFile(request, request.getParameter("getthumb"));
            if (file.exists()) {
                System.out.println(file.getAbsolutePath());
                String mimetype = getMimeType(file);
                if (mimetype.endsWith("png") || mimetype.endsWith("jpeg") || mimetype.endsWith("jpg") || mimetype.endsWith("gif")) {
                    BufferedImage im = ImageIO.read(file);
                    if (im != null) {
                        int newWidth = 75;
                        if (request.getParameter("w") != null) {
                            try {
                                newWidth = Integer.parseInt(request.getParameter("w"));
                            } catch (Exception e) {
                                //Se mantiene el valor por defecto de 75
                            }
                        }
                        
                        BufferedImage thumb = Scalr.resize(im, newWidth);
                        if (request.getParameter("h") != null) {
                            try {
                                thumb = Scalr.crop(thumb, newWidth, Integer.parseInt(request.getParameter("h")));
                            } catch (IllegalArgumentException | ImagingOpException e) {
                                //Se mantienen las proporciones.
                            }
                        }

                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        if (mimetype.endsWith("png")) {
                            ImageIO.write(thumb, "PNG", os);
                            response.setContentType("image/png");
                        } else if (mimetype.endsWith("jpeg")) {
                            ImageIO.write(thumb, "jpg", os);
                            response.setContentType("image/jpeg");
                        } else if (mimetype.endsWith("jpg")) {
                            ImageIO.write(thumb, "jpg", os);
                            response.setContentType("image/jpeg");
                        } else {
                            ImageIO.write(thumb, "GIF", os);
                            response.setContentType("image/gif");
                        }
                        try (ServletOutputStream srvos = response.getOutputStream()) {
                            response.setContentLength(os.size());
                            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
                            os.writeTo(srvos);
                            srvos.flush();
                        }
                    }
                }
            } // TODO: check and report success
        } else {
            PrintWriter writer = response.getWriter();
            writer.write("call POST with multipart form data");
        }
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }

        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        JsonArray json = new JsonArray();
        try {
            List<FileItem> items = uploadHandler.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    final String savedFile = UUID.randomUUID().toString() + "." + getSuffix(item.getName());
                    File file = new File(request.getServletContext().getRealPath("/") + "uploads/", savedFile);
                    item.write(file);
                    JsonObject jsono = new JsonObject();
                    jsono.addProperty("name", savedFile);
                    jsono.addProperty("path", file.getAbsolutePath());
                    jsono.addProperty("size", item.getSize());
                    json.add(jsono);
                    System.out.println(json.toString());
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            writer.write(json.toString());
            writer.close();
        }

    }

    private String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
            if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            } else if (getSuffix(file.getName()).equalsIgnoreCase("jpg")) {
                mimetype = "image/jpg";
            } else if (getSuffix(file.getName()).equalsIgnoreCase("jpeg")) {
                mimetype = "image/jpeg";
            } else if (getSuffix(file.getName()).equalsIgnoreCase("gif")) {
                mimetype = "image/gif";
            } else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype = mtMap.getContentType(file);
            }
        }
        return mimetype;
    }

    private String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
    }

    private File getFile(HttpServletRequest request, String thumbid) {
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        Image image = new Image();
        try {
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(image.getSelectSQL(Constants.LOAD))) {
                prepareStatement.setLong(1, Long.parseLong(thumbid));
                ResultSet result = prepareStatement.executeQuery();
                if (result.next()) {
                    image.setPath(result.getString("PATH"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new File(image.getPath());
    }
}
