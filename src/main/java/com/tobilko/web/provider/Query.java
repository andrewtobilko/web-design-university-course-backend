package com.tobilko.web.provider;

/**
 *
 * Created by Andrew Tobilko on 1/18/2017.
 *
 */
public final class Query {

    private final String template;
    private final Object[] arguments;

    public Query(String template, Object... arguments) {
        this.template = template;
        this.arguments = arguments;
    }

    public String getTemplate() {
        return template;
    }

    public Object[] getArguments() {
        return arguments;
    }

}
