/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis;

import com.jolbox.bonecp.BoneCP;
import com.nemesis.admin.BaseService;
import com.nemesis.entity.Ad;
import com.nemesis.entity.EntityUtil;
import com.nemesis.entity.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mcasallas
 */
@WebServlet(name = "PublicService", urlPatterns = {"/view"})
public class PublicService extends BaseService<Ad> {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String q = req.getParameter("q");
        if (q == null || q.trim().length() == 0) {
            List<Image> entities = new ArrayList<>();
            BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
            String query = "SELECT id, name from com_nemesis_entity.image \n"
                    + "where \n"
                    + "ismain = 1\n"
                    + "AND imguser in (\n"
                    + "select user1 from com_nemesis_entity.banner\n"
                    + "union \n"
                    + "select user2 from com_nemesis_entity.banner\n"
                    + "union\n"
                    + "select user3 from com_nemesis_entity.banner\n"
                    + "union \n"
                    + "select user4 from com_nemesis_entity.banner\n"
                    + "union \n"
                    + "select user5 from com_nemesis_entity.banner\n"
                    + " )";

            try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(query)) {
                ResultSet result = prepareStatement.executeQuery();
                while (result.next()) {
                    Image image = new Image();
                    image.setId(result.getLong("ID"));
                    image.setName(result.getString("NAME"));
                    entities.add(image);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PublicService.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("__list__", entities);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("view.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            List<Ad> entities = new ArrayList<>();
            BoneCP connectionPool = (BoneCP) getServletContext().getAttribute("__pool__");
            try {
                Ad ad = new Ad();
                final String selectSQL = ad.getSelectByCondition(" WHERE to_tsvector(title) @@ to_tsquery(?) OR to_tsvector(description) @@ to_tsquery(?)");
                try (Connection connection = connectionPool.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(selectSQL)) {
                    EntityUtil.setValues(prepareStatement, new Object[]{q, q});
                    ResultSet result = prepareStatement.executeQuery();
                    while (result.next()) {
                        ad = new Ad();
                        getRowEntity(ad, result);
                        entities.add(ad);
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(PublicService.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("__list__", entities);
            req.setAttribute("q", q);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("search.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

}
