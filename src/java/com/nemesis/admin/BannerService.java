/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.jolbox.bonecp.BoneCP;
import com.nemesis.entity.Banner;
import com.nemesis.entity.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "BannerService", urlPatterns = {"/admin/banner", "/admin/banner/create", "/admin/banner/edit/*", "/admin/banner/list", "/admin/banner/delete/*",})
public class BannerService extends BaseService<Banner> {

    @Override
    protected Class<Banner> getEntity() {
        return Banner.class;
    }

    @Override
    protected String getEditView(HttpServletRequest rq, HttpServletResponse response, boolean isCreate) {
        String title = "Editar el banner";
        String target = "../../banner_edit.jsp";
        if (isCreate) {
            if (limitReached(rq)) {
                rq.setAttribute("error", "Ya has creado el banner");
                try {
                    response.sendRedirect(getServletContext().getContextPath() + "/admin/banner");
                } catch (IOException ex) {
                    Logger.getLogger(AdsService.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
            title = "Crear el banner";
            target = "../banner_edit.jsp";
        }
        setTitle(rq, title);
        
        try {
            final List<User> entities = loadEntities(rq, User.class);
            rq.setAttribute("__list__", entities);
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(BannerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return target;
    }

    @Override
    protected String getSearchView(HttpServletRequest rq) {
        setTitle(rq, "Banner");
        return "banner.jsp";
    }

    @Override
    protected Banner beforeSave(HttpServletRequest req, Banner entity) {
        
        
        return entity;
    }

    @Override
    protected String afterSaveUrl() {
        return getServletContext().getContextPath() + "/admin/banner";
    }

    private boolean limitReached(HttpServletRequest rq) {
        String countSQL = new Banner().getCountSQL("");
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try {
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(countSQL)) {
                ResultSet result = prepareStatement.executeQuery();
                if (result.next()) {
                    return result.getInt(1) >= 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
