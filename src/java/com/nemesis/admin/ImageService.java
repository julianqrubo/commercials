/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.nemesis.admin.utils.Constants;
import com.nemesis.entity.Image;
import com.nemesis.entity.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "ImageService", urlPatterns = {"/admin/images", "/admin/images/create", "/admin/images/edit/*", "/admin/images/list", "/admin/images/delete/*",})
public class ImageService extends BaseService<Image> {

    @Override
    protected Class<Image> getEntity() {
        return Image.class;
    }

    @Override
    protected String getEditView(HttpServletRequest rq, boolean isCreate) {
        if (isCreate) {
            setTitle(rq, "Crear una im&aacute;gen");
            return "../image_edit.jsp";
        }
        setTitle(rq, "Editar una im&aacute;gen");
        return "../../image_edit.jsp";
    }

    @Override
    protected String getSearchView(HttpServletRequest rq) {
        setTitle(rq, "Buscar im&aacute;genes");
        return "images.jsp";
    }

    @Override
    protected String afterSaveUrl() {
        return getServletContext().getContextPath() + "/admin/images";
    }

    @Override
    protected Image beforeSave(HttpServletRequest req, Image image) {
        User user = (User) req.getSession().getAttribute(Constants.__USER__);
        if (user != null) {
            image.setImguser(user.getId());
        }
        return image;
    }

     @Override
    protected String getSearchCondition() {
        return " WHERE imguser = ?";
    }

    @Override
    protected Object[] getSearchValues(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.__USER__);
        if (user == null) {
            return new Object[]{-1l};
        }
        return new Object[]{user.getId()};
    }
}
