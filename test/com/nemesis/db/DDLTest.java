/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.db;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.nemesis.entity.Country;
import com.nemesis.entity.Department;
import com.nemesis.entity.City;
import com.nemesis.entity.Entity;
import com.nemesis.entity.User;
import java.sql.Connection;
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
            {new City()}
        });
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            Class.forName("org.postgresql.Driver"); 	// load the DB driver
            BoneCPConfig config = new BoneCPConfig();	// create a new configuration object
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/commercials2");	// set the JDBC url
            config.setUsername("sppyuser");			// set the username
            config.setPassword("Sh4r3pp7");				// set the password
            connectionPool = new BoneCP(config); 	// setup the connection pool
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
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
        if (connectionPool != null) {
            connectionPool.shutdown();
        }
    }

    @Test
    public void create_tables() throws SQLException {
//        final String schemaDDL = this.entity.getSchemaDDL();
//        System.out.println(schemaDDL);
//        statement.executeUpdate(schemaDDL);
        final String ddl = this.entity.getDDL();
        System.out.println(ddl);
        statement.executeUpdate(ddl);
    }
}
