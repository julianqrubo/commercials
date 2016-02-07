/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.config;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author shareppy
 */
@WebListener
public class ApplicationConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver"); 	// load the DB driver
            BoneCPConfig config = new BoneCPConfig();	// create a new configuration object
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/commercials");	// set the JDBC url
            config.setUsername("sppyuser");			// set the username
            config.setPassword("Sh4r3pp7");				// set the password
            BoneCP connectionPool = new BoneCP(config); 	// setup the connection pool
            sce.getServletContext().setAttribute("__pool__", connectionPool);
            sce.getServletContext().setAttribute("__ads_count__", 3);
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.INFO, "Se Ha inicializado el pool de conexiones.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.SEVERE, "Error al inicializar el pool de conexiones", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        BoneCP pool = (BoneCP) sce.getServletContext().getAttribute("__pool__");
        if (pool != null) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.INFO, "Se cierra el pool de conexiones a la base de datos");
            pool.shutdown();
        }
    }
}
