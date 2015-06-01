/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cleardev.servlets;

import com.cleardev.config.Params;
import com.cleardev.db.RowProcessor;
import com.jolbox.bonecp.BoneCP;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "Login", urlPatterns = {"/adm/login"})
public class Login extends BaseServlet {

    @Override
    protected String getView() {
        return "login.jsp";
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response, Connection connection)
            throws ServletException, IOException {
        System.out.println("Llego una petición");
        PrintWriter pw = response.getWriter();
        String username = request.getParameter(Params.USERNAME.toString());
        String password = request.getParameter(Params.PASSWORD.toString());
        response.setContentType("text/plain");
        StringBuilder resp = new StringBuilder("Error de inicio de sesión");
        query("SELECT   id, names, lastnames, username, password, info FROM security.user WHERE username = ? AND password = ?", new RowProcessor() {
            @Override
            public boolean processRow() {
                Long id = get("id");
                if( id != null ){
                    resp.delete(0,resp.length());
                    resp.append("Bienvenido...");
                }
                return false;
            }
        }, username, password);
        pw.print(resp.toString());
        pw.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
