package com.cleardev.filter.config;

public enum Params {

   CURRENT_USER("cuser");
    private String key;
   
   private Params(String key){
       this.key = key;
   }

   
   
    @Override
    public String toString() {
        return this.key;
    }
}
