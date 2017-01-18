package com.tobilko.web.provider;

import static com.tobilko.web.provider.Query.Mode.*;

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

    Mode getMode() {
        return arguments.length == 0 ? NO_PARAMETERS : (arguments.length > 1 ? LIST : MAP);
    }

    enum Mode {
        NO_PARAMETERS,
        LIST,
        MAP;
    }

}
