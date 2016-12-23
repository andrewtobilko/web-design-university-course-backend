package com.tobilko.web.stuff;


public enum Constant {

    QUERY_SAVE_FEEDBACK("INSERT INTO feedback (id, message, author, postedBy) VALUES (nextval('hibernate_sequence'), ?, ?, ?);"),
    QUERY_GET_ALL_FEEDBACK("SELECT * FROM feedback;"),
    QUERY_GET_FEEDBACK("SELECT * FROM feedback WHERE id = ?;"),

    SQL_DRIVER_NAME("org.postgresql.Driver"),
    SQL_URL("jdbc:postgresql://localhost:5432/shop"),
    SQL_USER_NAME("postgres"),
    SQL_PASSWORD("postgres"),

    HIBERNATE_CONFIG_FILE("hibernate.cfg.xml");

    private String value;

    Constant(String s) {
        value = s;
    }

    public String get() {
        return value;
    }

}
