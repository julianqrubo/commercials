/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.entity;

/**
 *
 * @author shareppy
 */
public class User extends Entity {

    private String names;
    private String last_names;
    private String email;
    private String username;
    private String password;
    private long rights;
    private long type;
    private long state;

    /**
     * @return the names
     */
    public String getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(String names) {
        this.names = names;
    }

    /**
     * @return the last_names
     */
    public String getLast_names() {
        return last_names;
    }

    /**
     * @param last_names the last_names to set
     */
    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rights
     */
    public long getRights() {
        return rights;
    }

    /**
     * @param rights the rights to set
     */
    public void setRights(long rights) {
        this.rights = rights;
    }

    /**
     * @return the state
     */
    public long getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(long state) {
        this.state = state;
    }

    public boolean getRight1() {
        return (getRights() & 1) == 1;
    }

    public boolean getRight2() {
        return (getRights() & 2) == 2;
    }

    public boolean getType1() {
        return (getType() & 1) == 1;
    }
    
    public boolean getType2() {
        return (getType() & 2) == 2;
    }
    
    public boolean getType3() {
        return (getType() & 3) == 3;
    }
    
    

    public String getStateText() {
        if (state == 0) {
            return "Inactivo";
        }
        return "Activo";
    }

    /**
     * @return the type
     */
    public long getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(long type) {
        this.type = type;
    }
}
