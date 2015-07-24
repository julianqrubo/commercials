/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.jolbox.bonecp.BoneCP;
import com.nemesis.entity.Entity;
import com.nemesis.entity.EntityUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.awt.X11.XConstants;

/**
 *
 * @author shareppy
 */
public abstract class BaseService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String[] parts = request.getRequestURI().split("/");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(getView(request, parts));
        requestDispatcher.forward(request, response);
    }
    
    protected <T extends Entity> void loadEntity(T entity) throws IllegalArgumentException, IllegalAccessException{
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try {
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(entity.getSelectSQL())) {
                prepareStatement.setLong(1, entity.getId());
                ResultSet result = prepareStatement.executeQuery();
                if(result.next()){
                    getRowEntity(entity, result);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    protected <T extends Entity> void getRowEntity(T entity, ResultSet result) throws IllegalArgumentException, SecurityException, IllegalAccessException, SQLException {
        List<Field> fields = EntityUtil.getFields(entity.getClass());
        for (Field field : fields) {
            Class<?> type = field.getType();
            field.setAccessible(true);
            if(EntityUtil.isInt(type) || EntityUtil.isLong(type)){
                field.setLong(entity, result.getLong(field.getName()));
            }else if(EntityUtil.isString(type)){
                field.set(entity, result.getString(field.getName()));
            }
        }
    }

    protected abstract String getView(HttpServletRequest rq, String... parts);

    protected String getPart(int index, String... parts) {
        if (parts != null && parts.length > index) {
            return parts[index];
        }
        return null;
    }

    protected boolean isCreate(String... parts) {
        if (parts == null) {
            return false;
        }
        return "create".equalsIgnoreCase(getPart(4, parts));
    }

    protected boolean isEdit(String... parts) {
        if (parts == null) {
            return false;
        }
        return "edit".equalsIgnoreCase(getPart(4, parts));
    }

    protected boolean isSearch(String... parts) {
        return !isCreate(parts) && !isEdit(parts);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            params.put(nextElement, req.getParameter(nextElement));
        }
        try {
            final String saveResult = save(EntityUtil.getEntity(getEntity(), req));
            if (saveResult != null) {
                resp.sendRedirect(saveResult);
            } else {
                try (PrintWriter writer = resp.getWriter()) {
                    writer.println("Sata saved...");
                    writer.flush();
                }
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected <T extends Entity> String save(T entity) throws IllegalArgumentException, IllegalAccessException {
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try {
            final String saveSQL = entity.getSaveSQL();
            System.out.println(saveSQL);
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(saveSQL)) {
                EntityUtil.setValues(prepareStatement, entity);
                prepareStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return afterSaveUrl();
    }

    protected String afterSaveUrl() {
        return null;
    }

    protected abstract Class<? extends Entity> getEntity();
}
