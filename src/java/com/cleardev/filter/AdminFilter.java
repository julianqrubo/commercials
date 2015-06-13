/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cleardev.filter;

import com.cleardev.config.Params;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shareppy
 */
@WebFilter(filterName = "AdminFilter", urlPatterns = {"/adm/*"})
public class AdminFilter implements Filter {

    public AdminFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpSession session = httpReq.getSession(true);
        if (httpReq.getMethod().equalsIgnoreCase("get")) {
            JsonObject current_user = (JsonObject) session.getAttribute(Params.CURRENT_USER.toString());
            if (current_user == null) {
                HttpServletResponse httpResp = (HttpServletResponse) response;
                RequestDispatcher requestDispatcher = httpReq.getRequestDispatcher("/adm/login");
                requestDispatcher.forward(request, response);
            }else{
            chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
