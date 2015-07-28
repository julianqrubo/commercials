/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.admin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shareppy
 */
@WebServlet(name = "AdsService", urlPatterns = {"/admin/ads"})
public class AdsService extends BaseService {

    @Override
    protected String getView(HttpServletRequest rq, String... parts) {
        return "ads.jsp";
    }
}
