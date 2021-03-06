/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.metadata;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author mcasallas
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Ref {
    Class value();
    String id() default "id";
    String label() default "name";
    String labelField();
}
