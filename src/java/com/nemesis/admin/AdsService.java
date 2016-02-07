/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.jolbox.bonecp.BoneCP;
import com.nemesis.admin.utils.Constants;
import com.nemesis.entity.Ad;
import com.nemesis.entity.Entity;
import com.nemesis.entity.EntityUtil;
import com.nemesis.entity.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "AdsService", urlPatterns = {"/admin/ads", "/admin/ads/create", "/admin/ads/edit/*", "/admin/ads/list", "/admin/ads/delete/*",})
public class AdsService extends BaseService {

    @Override
    protected String getEditView(HttpServletRequest rq, HttpServletResponse response, boolean isCreate) {
        if (isCreate) {
            if(limitReached(rq)){
                rq.setAttribute("error", "Has alcanzado el l&iacute;mite de anuncios");
                try {
                    response.sendRedirect(getServletContext().getContextPath() + "/admin/ads");
                } catch (IOException ex) {
                    Logger.getLogger(AdsService.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
            setTitle(rq, "Crear un anuncio");
            return "../ad_edit.jsp";
        }
        setTitle(rq, "Editar un anuncio");
        return "../../ad_edit.jsp";
    }

    @Override
    protected String getSearchView(HttpServletRequest rq) {
        setTitle(rq, "Buscar anuncios");
        return "ads.jsp";
    }

    @Override
    protected Class getEntity() {
        return Ad.class;
    }

    @Override
    protected String afterSaveUrl() {
        return getServletContext().getContextPath() + "/admin/ads";
    }

    @Override
    protected String getSearchCondition() {
        return " WHERE aduser = ?";
    }

    @Override
    protected Object[] getSearchValues(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.__USER__);
        if (user == null) {
            return new Object[]{-1l};
        }
        return new Object[]{user.getId()};
    }

    @Override
    protected Entity beforeSave(HttpServletRequest req, Entity entity) {
        User user = (User) req.getSession().getAttribute(Constants.__USER__);
        if (user != null) {
            Ad ad = (Ad)entity;
            ad.setAduser(user.getId());
        }
        return entity;
    }

    private boolean limitReached(HttpServletRequest rq) {
        String countSQL = new Ad().getCountSQL(" WHERE aduser = ?");
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        User user = (User) rq.getSession().getAttribute(Constants.__USER__);
        int max_ads = (int) getServletContext().getAttribute("__ads_count__");
        try {
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(countSQL)) {
                EntityUtil.setLongValue(prepareStatement, user == null ? -1l: user.getId());
                ResultSet result = prepareStatement.executeQuery();
                if (result.next()) {
                    return result.getInt(1)>= max_ads;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
