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
public class Image extends Entity{

    private String name;
    private String path;
    private long ismain;
    private long imguser;

    public Image() {
        super();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the ismain
     */
    public long getIsmain() {
        return ismain;
    }
    
    public String getMainImage(){
        return getIsmain() > 0 ? "Si" : "No"; 
    }
    
    /**
     * @param ismain the ismain to set
     */
    public void setIsmain(long ismain) {
        this.ismain = ismain;
    }

    /**
     * @return the imguser
     */
    public long getImguser() {
        return imguser;
    }

    /**
     * @param imguser the imguser to set
     */
    public void setImguser(long imguser) {
        this.imguser = imguser;
    }
}