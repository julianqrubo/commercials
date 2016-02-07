/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.db;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.nemesis.entity.Ad;
import com.nemesis.entity.Banner;
import com.nemesis.entity.City;
import com.nemesis.entity.Country;
import com.nemesis.entity.Department;
import com.nemesis.entity.Entity;
import com.nemesis.entity.EntityUtil;
import com.nemesis.entity.Image;
import com.nemesis.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author shareppy
 */
@RunWith(Parameterized.class)
public class DDLTest {

    private static BoneCP connectionPool;
    private final Entity entity;
    private Connection conn;
    private Statement statement;

    @Parameters
    public static Collection<Entity[]> data() {
        return Arrays.asList(new Entity[][]{
            {new User()},
            {new Country()},
            {new Department()},
            {new City()},
            {new Ad()},
            {new Image()},
            {new Banner()}
        });
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            Class.forName("org.postgresql.Driver"); 	// load the DB driver
            BoneCPConfig config = new BoneCPConfig();	// create a new configuration object
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/commercials");	// set the JDBC url
            config.setUsername("sppyuser");			// set the username
            config.setPassword("Sh4r3pp7");				// set the password
            connectionPool = new BoneCP(config); 	// setup the connection pool
            createSchema();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

    }

    private static void createSchema() {
        try {
            User user = new User();
            Connection init_conn = connectionPool.getConnection();
            Statement init_statement = init_conn.createStatement();
            init_statement.executeUpdate(user.getSchemaDDL());
        } catch (SQLException ex) {
            Logger.getLogger(DDLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DDLTest(Entity entity) {
        this.entity = entity;
    }

    @Before
    public void open() {
        try {
            this.conn = connectionPool.getConnection();
            this.statement = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DDLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void close() {
        if (this.conn != null) {
            try {
                this.statement.close();
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DDLTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @AfterClass
    public static void tearDownClass() {
        insetInitialData();
        if (connectionPool != null) {
            connectionPool.shutdown();
        }
    }

    private static void insetInitialData() {
        try {
            User user = new User();
            user.setNames("Administrador");
            user.setLast_names("del Sistema");
            user.setEmail("admin@nemesis.com");
            user.setUsername("admin");
            user.setPassword("123456");
            user.setType(1);
            user.setRights(3);
            user.setState(1);
            Connection init_conn = connectionPool.getConnection();
            PreparedStatement init_statement = init_conn.prepareStatement(user.getSaveSQL());
            EntityUtil.setValues(init_statement, user);
            init_statement.executeUpdate();

            for (int i = 0; i < 10; i++) {
                user = new User();
                user.setNames("Nombre"+i);
                user.setLast_names("Apellido"+i);
                user.setEmail("usuario"+i+"@nemesis.com");
                user.setUsername("usuario"+i);
                user.setPassword("123456");
                user.setType(1);
                user.setRights(1);
                user.setState(1);
                init_statement = init_conn.prepareStatement(user.getSaveSQL());
                EntityUtil.setValues(init_statement, user);
                init_statement.executeUpdate();
            }
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException ex) {
            Logger.getLogger(DDLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void create_tables() throws SQLException {
        final String ddl = this.entity.getDDL();
        System.out.println(ddl);
        statement.executeUpdate(ddl);

    }
}
