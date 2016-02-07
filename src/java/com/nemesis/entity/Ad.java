/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.entity;

import com.nemesis.metadata.Fts;
import com.nemesis.metadata.Ref;

/**
 *
 * @author mcasallas
 */
public class Ad extends Entity{
    
    @Ref(value=Country.class, labelField = "countryLabel")
    private long country;
    
    @Ref(value=Department.class, labelField = "departmentLabel")
    private long department;
    
    @Ref(value=City.class, labelField = "cityLabel")
    private long city;
    
    @Fts
    private String title;
    
    @Fts
    private String description;
    
    private long phone1;
    
    private long phone2;
    
    private long phone3;
    
    private long aduser;

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

    /**
     * @return the department
     */
    public long getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(long department) {
        this.department = department;
    }

    /**
     * @return the city
     */
    public long getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(long city) {
        this.city = city;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the phone1
     */
    public long getPhone1() {
        return phone1;
    }

    /**
     * @param phone1 the phone1 to set
     */
    public void setPhone1(long phone1) {
        this.phone1 = phone1;
    }

    /**
     * @return the phone2
     */
    public long getPhone2() {
        return phone2;
    }

    /**
     * @param phone2 the phone2 to set
     */
    public void setPhone2(long phone2) {
        this.phone2 = phone2;
    }

    /**
     * @return the phone3
     */
    public long getPhone3() {
        return phone3;
    }

    /**
     * @param phone3 the phone3 to set
     */
    public void setPhone3(long phone3) {
        this.phone3 = phone3;
    }

    /**
     * @return the user
     */
    public long getAduser() {
        return aduser;
    }

    /**
     * @param aduser the user to set
     */
    public void setAduser(long aduser) {
        this.aduser = aduser;
    }
}
