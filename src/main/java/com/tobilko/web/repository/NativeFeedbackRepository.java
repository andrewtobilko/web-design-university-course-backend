package com.tobilko.web.repository;

import com.tobilko.web.entity.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.tobilko.web.stuff.Constant.*;

public class NativeFeedbackRepository implements Repository<Feedback> {

    private Connection connection;

    {
        try {
            Class.forName(SQL_DRIVER_NAME.get());
            connection = DriverManager.getConnection(SQL_URL.get(), SQL_USER_NAME.get(), SQL_PASSWORD.get());
        } catch (ClassNotFoundException e) {
            System.out.println("The driver class could not be found!");
        } catch (SQLException e) {
            System.out.println("The connection could not be open!");
        }
    }

    @Override
    public void save(Feedback feedback) {
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_SAVE_FEEDBACK.get());

            statement.setString(1, feedback.getMessage());
            statement.setString(2, feedback.getAuthor());
            statement.setDate(3, new Date(feedback.getPostedBy().getTime()));

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Feedback> getAll() {
        List<Feedback> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_GET_ALL_FEEDBACK.get());

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Feedback feedback = new Feedback();

                feedback.setId(Long.valueOf(set.getString(1)));
                feedback.setAuthor(set.getString(2));
                feedback.setMessage(set.getString(3));
                feedback.setPostedBy(set.getDate(4));

                list.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    private class PreparedStatementBuilder {
        private PreparedStatement statement;

        public PreparedStatementBuilder(PreparedStatement preparedStatement) {
            this.statement = preparedStatement;
        }

        public PreparedStatementBuilder buildPart(int index, String s) {
            try {
                statement.setString(index, s);
            } catch (SQLException e) {
                throw new IllegalArgumentException("Could not build this part!");
            }
            return this;
        }

        public PreparedStatementBuilder buildPart(int index, Date d) {
            try {
                statement.setDate(index, d);
            } catch (SQLException e) {
                throw new IllegalArgumentException("Could not build this part!");
            }
            return this;
        }

        public PreparedStatement build() {
            return statement;
        }

    }

}
