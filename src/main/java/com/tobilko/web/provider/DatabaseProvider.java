package com.tobilko.web.provider;

import java.sql.*;

import static com.tobilko.web.stuff.Constant.*;

/**
 *
 * Created by Andrew Tobilko on 1/17/2017.
 *
 */
public class DatabaseProvider {

    private DatabaseOptions options;

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
        ResultSet result = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query.getTemplate());

            Object[] arguments = query.getArguments();
            for (int i = 0; i < arguments.length; i++) {
                statement.setString(i + 1, arguments[i].toString());
            }

            result = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public int executeUpdateQuery(Query query) {

    }

}

class A {
    public static void main(String[] args) {

        DatabaseProvider database = DatabaseProvider.createFromOptions(new SimpleDatabaseOptions(SQL_URL.get(), SQL_USER_NAME.get(), SQL_PASSWORD.get()));

        database.executeQuery(new Query("SELECT * FROM feedbacks"));
    }
}
