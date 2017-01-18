package com.tobilko.web.provider;

/**
 *
 * Created by Andrew Tobilko on 1/17/2017.
 *
 */
public class SimpleDatabaseOptions implements DatabaseOptions {

    private String login;
    private String password;
    private String url;

    public SimpleDatabaseOptions(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUrl() {
        return url;
    }

}
