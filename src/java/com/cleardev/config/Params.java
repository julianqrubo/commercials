package com.cleardev.config;

public enum Params {

   CURRENT_USER, USERNAME, PASSWORD, POOL;
   
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
