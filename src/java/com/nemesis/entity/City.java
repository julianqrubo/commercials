/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.entity;

import com.nemesis.metadata.Ref;

/**
 *
 * @author shareppy
 */
public class City extends Entity{
    
    @Ref(value=Country.class, labelField = "countryLabel")
    private long country;
    @Ref(value=Department.class, labelField = "departmentLabel")
    private long department;
    
    private String name;
    private String code;
    
    public City() {
        super();
    }
    
    /**
     * @return the country
     */
    public long getCountry() {
        return country;
    }
    
    /**
     * @return the department
     */
    public long getDepartment() {
        return department;
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
     * @param country the country to set
     */
    public void setCountry(long country) {
        this.country = country;
    }
    
    /**
     * @param department the department to set
     */
    public void setDepartment(long department) {
        this.department = department;
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
}
