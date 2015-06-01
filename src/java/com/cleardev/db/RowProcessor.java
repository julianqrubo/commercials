/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cleardev.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shareppy
 */
public abstract class RowProcessor {
    private ResultSet set;

    public RowProcessor() {
    }
    
    public abstract boolean processRow();
    
    public void setResult(ResultSet set){
        this.set = set;
    }
    
    protected <T> T get(String column){
        try {
            return (T) set.getObject(column);
        } catch (SQLException ex) {
            Logger.getLogger(RowProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
