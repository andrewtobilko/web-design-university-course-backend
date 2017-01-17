package com.tobilko.web.provider;

/**
 *
 * Created by Andrew Tobilko on 1/17/2017.
 *
 */
public interface DatabaseOptions {

    String getLogin();
    String getPassword();
    String getDatabaseName();

    // other options may be added

}
