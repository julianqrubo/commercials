package com.cleardev.config;

public enum Params {

   CURRENT_USER, USERNAME, PASSWORD, POOL, REQUESTED_PATH;
   
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
