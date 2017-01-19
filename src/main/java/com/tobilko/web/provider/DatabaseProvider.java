package com.tobilko.web.provider;

import java.sql.*;
import java.util.Map;

import static com.tobilko.web.stuff.Constant.SQL_DRIVER_NAME;
import static java.util.stream.IntStream.range;

/**
 *
 * Created by Andrew Tobilko on 1/17/2017.
 *
 */
public class DatabaseProvider {

    private DatabaseProvider() {}

    private static DatabaseProvider instance;
    private static Connection connection;

    public static DatabaseProvider createFromOptions(DatabaseOptions options) {
        try {
            Class.forName(SQL_DRIVER_NAME.get());
            connection = DriverManager.getConnection(options.getUrl(), options.getLogin(), options.getPassword());
        } catch (ClassNotFoundException e) {
            System.out.println("The driver class could not be found!");
        } catch (SQLException e) {
            System.out.println("The connection could not be open!");
        }
        return instance == null ? (instance = new DatabaseProvider()) : instance;
    }

    public ResultSet executeQuery(Query query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query.getTemplate());
            switch (query.getMode()) {
                case NO_PARAMETERS:
                    return (statement).executeQuery();
                case MAP:
                    return executeQueryWithNamedParameters(connection.createStatement(), query);
                case ARRAY:
                    return executeQueryWithNamelessParameters(statement, query);
            }
        } catch (SQLException e) {
            System.out.println("Could not either form a statement or execute that.");
        }
        return null;
    }
    public int executeUpdateQuery(Query query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query.getTemplate());
            switch (query.getMode()) {
                case NO_PARAMETERS:
                    return (statement).executeUpdate();
                case MAP:
                    return executeUpdateQueryWithNamedParameters(connection.createStatement(), query);
                case ARRAY:
                    return executeUpdateQueryWithNamelessParameters(statement, query);
            }
        } catch (SQLException e) {
            System.out.println("Could not either form an update statement or execute that: " +  e.getMessage());
        }
        return 0;
    }

    private ResultSet executeQueryWithNamedParameters(Statement statement, Query query) throws SQLException {
        return statement.executeQuery(formTemplateFromMap(query.getTemplate(), query));
    }
    private int executeUpdateQueryWithNamedParameters(Statement statement, Query query) throws SQLException {
        return statement.executeUpdate(formTemplateFromMap(query.getTemplate(), query));
    }

    private ResultSet executeQueryWithNamelessParameters(PreparedStatement statement, Query query) throws SQLException {
        Object[] parameters = query.getArguments();
        range(0, parameters.length).forEach(i -> setParameter(statement, parameters[i], i + 1));
        return statement.executeQuery();
    }
    private int executeUpdateQueryWithNamelessParameters(PreparedStatement statement, Query query) throws SQLException {
        Object[] parameters = query.getArguments();
        range(0, parameters.length).forEach(i -> setParameter(statement, parameters[i], i + 1));
        return statement.executeUpdate();
    }

    private void setParameter(PreparedStatement statement, Object argument, int position) {
        try {
            Class<?> argumentClass = argument.getClass();

            // all supported classes - String, Long, Date
            if (String.class.isAssignableFrom(argumentClass)) {
                statement.setString(position, argument.toString());
            } else if (Long.class.isAssignableFrom(argumentClass)) {
                statement.setLong(position, (Long) argument);
            } else if (Date.class.isAssignableFrom(argumentClass)) {
                statement.setDate(position, (Date) argument);
            } else {
                System.out.println("There is no support for parameters of such a type.");
            }

        } catch (SQLException e) {
            System.out.println("Could not set a parameter " + argument + " into a position " + position + ".");
        }
    }
    private String formTemplateFromMap(String template, Query query) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) query.getArguments()[0];

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String namedParameter = ":" + entry.getKey();
            if (template.contains(namedParameter)) {
                Object value = entry.getValue();
                Class<?> valueClass = value.getClass();
                if (String.class.isAssignableFrom(valueClass)) {
                    template = template.replace(namedParameter, "'" + value + "'");
                } else if (Long.class.isAssignableFrom(valueClass)) {
                    template = template.replace(namedParameter, String.valueOf(value));
                } else {
                    System.out.println("There is no support for parameters of such a type.");
                }
            } else {
                System.out.println("It has been passed a wrong map with parameters!");
            }
        }
        return template;
    }

}
