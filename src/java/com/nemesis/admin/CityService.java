/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.nemesis.entity.City;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "CityService", urlPatterns = {"/admin/cities", "/admin/cities/create", "/admin/cities/edit/*", "/admin/cities/list", "/admin/cities/delete/*"})
public class CityService extends BaseService<City> {

    @Override
    protected Class<City> getEntity() {
        return City.class;
    }
    
    @Override
    protected String getEditView(HttpServletRequest rq, boolean isCreate) {
        if (isCreate) {
            setTitle(rq, "Crear una ciudad");
            return "../city_edit.jsp";
        }
        rq.setAttribute("_title_", "Editar una ciudad");
        return "../../city_edit.jsp";
    }

    @Override
    protected String getSearchView(HttpServletRequest rq) {
        setTitle(rq, "Buscar ciudades");
        return "cities.jsp";
    }

    @Override
    protected String afterSaveUrl() {
        return getServletContext().getContextPath() + "/admin/cities";
    }
}