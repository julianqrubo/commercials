/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.nemesis.entity.Country;
import com.nemesis.entity.EntityUtil;
import com.nemesis.entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "CountryService", urlPatterns = {"/admin/countries", "/admin/countries/create", "/admin/countries/edit/*", "/admin/countries/list"})
public class CountryService extends BaseService<Country> {

    @Override
    protected Class<Country> getEntity() {
        return Country.class;
    }
    
    @Override
    protected String getEditView(HttpServletRequest rq, boolean isCreate) {
        if (isCreate) {
           setTitle(rq, "Crear un país");
            return "../country_edit.jsp";
        }
        setTitle(rq, "Editar un país");
        return "../../country_edit.jsp";
    }

    @Override
    protected String getSearchView(HttpServletRequest rq) {
        setTitle(rq, "Buscar paises");
        return "countries.jsp";
    }

    @Override
    protected String afterSaveUrl() {
        return getServletContext().getContextPath() + "/admin/countries";
    }
}
