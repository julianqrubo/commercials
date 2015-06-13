/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cleardev.servlets;

import com.cleardev.config.Params;
import com.cleardev.db.RowProcessor;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
        PrintWriter pw = response.getWriter();
        String username = request.getParameter(Params.USERNAME.toString());
        String password = request.getParameter(Params.PASSWORD.toString());
        response.setContentType("text/plain");
        StringBuilder resp = new StringBuilder("Error de inicio de sesión");
        query("SELECT   id, names, lastnames, username, password, info FROM security.user WHERE username = ? AND password = ?", new RowProcessor() {
            @Override
            public boolean processRow() throws Exception{
                Long id = get("id");
                String lastnames = get("lastnames");
                String username = get("username");
                String info = get("info");
                if( id != null ){
                    resp.delete(0,resp.length());
                    resp.append("Bienvenido...");
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id",  Long.valueOf((Long)get("id")));
                    jsonObject.addProperty("names", (String)get("names"));
                    jsonObject.addProperty("lastnames", (String)get("lastnames"));
                    jsonObject.addProperty("username", (String)get("username"));
                    jsonObject.addProperty("info", (String)get("info"));
                    request.getSession(true).setAttribute(Params.CURRENT_USER.toString(), jsonObject);
                    response.sendRedirect((String) request.getSession(true).getAttribute(Params.REQUESTED_PATH.toString()));
                }
                return false;
            }
        }, username, password);
        pw.print(resp.toString());
        pw.close();
    }
}