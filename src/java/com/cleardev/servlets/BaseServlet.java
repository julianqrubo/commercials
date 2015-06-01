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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shareppy
 */
public abstract class BaseServlet extends HttpServlet {

    protected abstract String getView();

    protected abstract void post(HttpServletRequest request, HttpServletResponse response, Connection connection) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(getView());
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BoneCP pool = (BoneCP) getServletContext().getAttribute(Params.POOL.toString());
        Connection connection = null;
        try {
            connection = pool.getConnection();
            post(request, response, pool.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void query(String query, RowProcessor processor, Object... params) {
        BoneCP pool = (BoneCP) getServletContext().getAttribute(Params.POOL.toString());
        try (Connection connection = pool.getConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            setValues(prepareStatement, params);
            ResultSet executeQuery = prepareStatement.executeQuery();
            boolean loop = true;
            processor.setResult(executeQuery);
            while (loop && executeQuery.next()) {
                loop = processor.processRow();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setValues(PreparedStatement prepareStatement, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                setValue(prepareStatement, i + 1, params[i]);
            }
        }
    }

    private void setValue(PreparedStatement prepareStatement, int i, Object param) throws SQLException {
        if (param instanceof String) {
            prepareStatement.setString(i, (String) param);
        } else if (param instanceof Long) {
            prepareStatement.setLong(i, (Long) param);
        } else if (param instanceof Timestamp) {
            prepareStatement.setTimestamp(i, (Timestamp) param);
        } else {
            prepareStatement.setObject(i, param);
        }
    }
}
