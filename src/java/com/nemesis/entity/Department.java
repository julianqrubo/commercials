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
public class Department extends Entity{
    
    private long country;
    private String name;
    private String code;

    public Department() {
        super();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the country
     */
    public long getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(long country) {
        this.country = country;
    }
}