/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.jolbox.bonecp.BoneCP;
import com.nemesis.entity.Entity;
import com.nemesis.entity.EntityUtil;
import com.nemesis.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "UserService", urlPatterns = {"/admin/users", "/admin/users/create", "/admin/users/edit/*"})
public class UserService extends BaseService {

    @Override
    protected String getView(HttpServletRequest rq, String... parts) {
        System.out.println(Arrays.toString(parts));
        if (isCreate(parts)) {
            rq.setAttribute("_title_", "Crear un usuario");
            return "../user_edit.jsp";
        }
        if (isEdit(parts)) {
            final String id = getPart(5, parts);
            rq.setAttribute("id", id);
            rq.setAttribute("_entity_", new User());
            rq.setAttribute("_title_", "Editar un usuario");
            try {
                final User user = new User();
                user.setId(EntityUtil.getLong(id));
                loadEntity(user);
                System.out.println(user);
                rq.setAttribute("_entity_", user);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "../../user_edit.jsp";
        }
        try {
            rq.setAttribute("__list__", loadEntities());
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "users.jsp";
    }

    private List<? extends Entity> loadEntities() throws IllegalArgumentException, IllegalAccessException {
        List<User> users = new ArrayList<>();
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try {
            User user = new User();
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(user.getSelectSQL(true))) {
                ResultSet result = prepareStatement.executeQuery();
                while (result.next()) {
                    user = new User();
                    getRowEntity(user, result);
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    protected String afterSaveUrl() {
        return getServletContext().getContextPath() + "/admin/users";
    }

    @Override
    protected Class<? extends Entity> getEntity() {
        return User.class;
    }
}
