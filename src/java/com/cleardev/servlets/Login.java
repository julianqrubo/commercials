/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cleardev.servlets;

import com.cleardev.config.Params;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DefaultEditorKit;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "Login", urlPatterns = {"/adm/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Llego una petición");
        PrintWriter pw = response.getWriter();
        String username = request.getParameter(Params.USERNAME.toString());
        String password = request.getParameter(Params.PASSWORD.toString());
        response.setContentType("text/plain");

        BoneCP pool = (BoneCP) getServletContext().getAttribute(Params.POOL.toString());
        Connection connection = null;

           boolean into = false;
        try {
            connection = pool.getConnection();
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT   id, names, lastnames, username, password, info FROM security.user WHERE username = ? AND password = ?");
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            ResultSet executeQuery = prepareStatement.executeQuery();
            if( executeQuery.next() ){
                into = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(into){
            pw.println("Bienvenido...");
        }else{
                pw.println("Error de inicio de sesión...");
        }
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
