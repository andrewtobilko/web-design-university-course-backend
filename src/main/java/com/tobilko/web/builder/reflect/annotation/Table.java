package com.tobilko.web.builder.reflect.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * Created by Andrew Tobilko on 12/29/2016.
 *
 */
@Retention(RUNTIME)
public @interface Table {

    String value();

}
