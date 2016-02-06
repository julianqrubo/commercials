/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.jolbox.bonecp.BoneCP;
import com.nemesis.admin.utils.Constants;
import com.nemesis.entity.EntityUtil;
import com.nemesis.entity.User;
import java.io.IOException;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "HomeService", urlPatterns = {"/admin", "/admin/logout"})
public class HomeService extends BaseService {

    @Override
    protected String getView(HttpServletRequest rq, String... parts) {
        final HttpSession session = rq.getSession(true);
        if( "logout".equals(getPart(3, parts)) ){
            session.removeAttribute(Constants.__USER__);
            return "login.jsp";
        }
        if( session.getAttribute(Constants.__USER__) != null){
            return "admin/home.jsp";
        }
        return "admin/login.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User();
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(user.getSelectByCondition(" WHERE username = ? AND password = ?"))) {
            EntityUtil.setStringValue(prepareStatement, username);
            EntityUtil.setStringValue(prepareStatement, password, 2);
            final ResultSet set = prepareStatement.executeQuery();
            if (set.next()) {
                user.setId(set.getLong("ID"));
                user.setNames(set.getString("NAMES"));
                user.setLast_names(set.getString("LAST_NAMES"));
                user.setEmail(set.getString("EMAIL"));
                user.setUsername(set.getString("USERNAME"));
                user.setRights(set.getLong("RIGHTS"));
                user.setType(set.getLong("TYPE"));
                user.setState(set.getLong("STATE"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user.getId() > 0) {
            if (user.getState() > 0) {
                final HttpSession session = req.getSession(true);
                session.setAttribute(Constants.__USER__, user);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/home.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                req.setAttribute("error", "El usuario se encuentra inactivo");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/login.jsp");
                requestDispatcher.forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Usuario o contraseña inválidos");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin/login.jsp");
            requestDispatcher.forward(req, resp);
        }

    }
}
