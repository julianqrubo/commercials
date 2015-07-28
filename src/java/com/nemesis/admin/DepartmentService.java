/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.nemesis.entity.Department;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "DepartmentService", urlPatterns = {"/admin/departments", "/admin/departments/create", "/admin/departments/edit/*"})
public class DepartmentService extends BaseService<Department> {

    @Override
    protected Class<Department> getEntity() {
        return Department.class;
    }
    
    @Override
    protected String getEditView(HttpServletRequest rq, boolean isCreate) {
        if (isCreate) {
            setTitle(rq, "Crear un departamento");
            return "../department_edit.jsp";
        }
        rq.setAttribute("_title_", "Editar un departamento");
        return "../../department_edit.jsp";
    }

    @Override
    protected String getSearchView(HttpServletRequest rq) {
        setTitle(rq, "Buscar departamentos");
        return "departments.jsp";
    }

    @Override
    protected String afterSaveUrl() {
        return getServletContext().getContextPath() + "/admin/departments";
    }
}
