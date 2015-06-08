/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cleardev.servlets;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "Commercials_info", urlPatterns = {"/adm/commercials_info"})
public class Commercials_info extends BaseServlet {
    
    @Override
    protected String getView() {
        return "commercials_info.jsp";
    }

    @Override
    protected void post(HttpServletRequest request, HttpServletResponse response, Connection connection) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
