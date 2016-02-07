/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.nemesis.entity.EntityUtil;
import com.nemesis.entity.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "UserService", urlPatterns = {"/admin/users", "/admin/users/create", "/admin/users/edit/*"})
public class UserService extends BaseService<User> {

    @Override
    protected String getEditView(HttpServletRequest rq, boolean isCreate) {
        if (isCreate) {
            setTitle(rq, "Crear un usuario");
            return "../user_edit.jsp";
        }
        setTitle(rq, "Editar un usuario");
        return "../../user_edit.jsp";
    }

    @Override
    protected String getSearchView(HttpServletRequest rq) {
        setTitle(rq, "Buscar usuarios");
        return "users.jsp";
    }

    @Override
    protected String afterSaveUrl() {
        return getServletContext().getContextPath() + "/admin/users";
    }

    @Override
    protected Class<User> getEntity() {
        return User.class;
    }

    @Override
    protected User beforeSave(HttpServletRequest req, User entity) {
        final long right1 = EntityUtil.getLong(req, "right1");
        final long right2 = EntityUtil.getLong(req, "right2");
        entity.setRights(right1 | right2);
        return entity;
    }
}
