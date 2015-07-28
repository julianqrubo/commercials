/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.jolbox.bonecp.BoneCP;
import com.nemesis.entity.Department;
import com.nemesis.entity.Entity;
import com.nemesis.entity.User;
import com.nemesis.entity.EntityUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @param <T>
 */
public abstract class BaseService<T extends Entity> extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String[] parts = request.getRequestURI().split("/");
        if (isList(parts)) {
            try {
                sendList(request, response, getPart(5, parts));
            } catch (InstantiationException | IllegalAccessException e) {
                throw new ServletException("Error al mostrar la vista", e);
            }
            return;
        }
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(getView(request, parts));
            requestDispatcher.forward(request, response);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServletException("Error al mostrar la vista", e);
        }
    }

    protected void setTitle(HttpServletRequest rq, String title) {
        rq.setAttribute("_title_", title);
    }

    protected String getView(HttpServletRequest rq, String... parts) throws InstantiationException, IllegalAccessException {
        if (isCreate(parts)) {
            return getEditView(rq, true);
        }
        if (isEdit(parts)) {
            final String id = getPart(5, parts);
            rq.setAttribute("id", id);
            rq.setAttribute("_entity_", getEntity().newInstance());
            try {
                final T entty = getEntity().newInstance();
                entty.setId(EntityUtil.getLong(id));
                loadEntity(entty);
                rq.setAttribute("_entity_", entty);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
            return getEditView(rq, false);
        }
        try {
            rq.setAttribute("__list__", loadEntities());
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getSearchView(rq);
    }

    protected String getEditView(HttpServletRequest request, boolean isCreate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected String getSearchView(HttpServletRequest rq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected void loadEntity(T entity) throws IllegalArgumentException, IllegalAccessException {
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try {
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(entity.getSelectSQL())) {
                prepareStatement.setLong(1, entity.getId());
                ResultSet result = prepareStatement.executeQuery();
                if (result.next()) {
                    getRowEntity(entity, result);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void getRowEntity(T entity, ResultSet result) throws IllegalArgumentException, SecurityException, IllegalAccessException, SQLException {
        List<Field> fields = EntityUtil.getFields(entity.getClass());
        for (Field field : fields) {
            Class<?> type = field.getType();
            field.setAccessible(true);
            if (EntityUtil.isInt(type) || EntityUtil.isLong(type)) {
                field.setLong(entity, result.getLong(field.getName()));
            } else if (EntityUtil.isString(type)) {
                field.set(entity, result.getString(field.getName()));
            }
        }
    }

    protected String getPart(int index, String... parts) {
        if (parts != null && parts.length > index) {
            return parts[index];
        }
        return null;
    }

    protected boolean isCreate(String... parts) {
        if (parts == null) {
            return false;
        }
        return "create".equalsIgnoreCase(getPart(4, parts));
    }

    protected boolean isEdit(String... parts) {
        if (parts == null) {
            return false;
        }
        return "edit".equalsIgnoreCase(getPart(4, parts));
    }

    protected boolean isList(String... parts) {
        if (parts == null) {
            return false;
        }
        return "list".equalsIgnoreCase(getPart(4, parts));
    }

    protected boolean isSearch(String... parts) {
        return !isCreate(parts) && !isEdit(parts);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            params.put(nextElement, req.getParameter(nextElement));
        }
        try {
            final String saveResult = save(beforeSave(req, EntityUtil.getEntity(getEntity(), req)));
            if (saveResult != null) {
                resp.sendRedirect(saveResult);
            } else {
                try (PrintWriter writer = resp.getWriter()) {
                    writer.println("Sata saved...");
                    writer.flush();
                }
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected T beforeSave(HttpServletRequest req, T entity) {
        return entity;
    }

    protected String save(T entity) throws IllegalArgumentException, IllegalAccessException {
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try {
            final String saveSQL = entity.getSaveSQL();
            System.out.println(saveSQL);
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(saveSQL)) {
                EntityUtil.setValues(prepareStatement, entity);
                prepareStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return afterSaveUrl();
    }

    protected String afterSaveUrl() {
        return null;
    }

    protected Class<T> getEntity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected List<T> loadEntities() throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        List<T> entities = new ArrayList<>();
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try {
            T entity = getEntity().newInstance();
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(entity.getSelectSQL(true))) {
                ResultSet result = prepareStatement.executeQuery();
                while (result.next()) {
                    entity = getEntity().newInstance();
                    getRowEntity(entity, result);
                    entities.add(entity);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entities;
    }

    private void sendList(HttpServletRequest request, HttpServletResponse response, String part) throws InstantiationException, IllegalAccessException, IOException {
        List<T> entities = new ArrayList<>();
        BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
        try {
            T entity = getEntity().newInstance();
            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(entity.getSelectSQL(2))) {
                prepareStatement.setString(1, request.getParameter("q").concat("%"));
                ResultSet result = prepareStatement.executeQuery();
                while (result.next()) {
                    entity = getEntity().newInstance();
                    getRowEntity(entity, result);
                    entities.add(entity);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sendJSON(response, new GsonBuilder().create().toJson(entities));
    }
    
    protected void sendJSON(HttpServletResponse response, String json) throws IOException{
        response.setContentType("application/json");
        try( PrintWriter writer = response.getWriter()){
            writer.print(json);
            writer.flush();
        }
        
    }
}
