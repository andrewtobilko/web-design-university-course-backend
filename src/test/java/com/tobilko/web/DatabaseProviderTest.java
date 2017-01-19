package com.tobilko.web;

import com.tobilko.web.provider.DatabaseProvider;
import com.tobilko.web.provider.Query;
import com.tobilko.web.provider.SimpleDatabaseOptions;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import static com.tobilko.web.stuff.Constant.*;
import static java.lang.String.join;
import static java.lang.String.valueOf;

/**
 *
 * Created by Andrew Tobilko on 1/19/2017.
 *
 */
@SpringBootTest
public class DatabaseProviderTest {

    @Test
    public void test() throws SQLException {

        DatabaseProvider database = DatabaseProvider.createFromOptions(new SimpleDatabaseOptions(SQL_URL.get(), SQL_USER_NAME.get(), SQL_PASSWORD.get()));

        ResultSet set = database.executeQuery(new Query("SELECT * FROM feedback"));
        printFromResultSet(set);

        ResultSet set2 = database.executeQuery(new Query("SELECT * FROM feedback WHERE id > ?", 7L));
        printFromResultSet(set2);

        int result = database.executeUpdateQuery(new Query("INSERT INTO feedback VALUES (?, ?, ?, ?)", 27L, "andrew", "test", Date.valueOf(LocalDate.now())));
        System.out.println(result);

        int result2 = database.executeUpdateQuery(new Query("INSERT INTO feedback VALUES (:p1, :p2)", new HashMap<String, Object>() {{
            this.put("p1", 29L);
            this.put("p2", "author");
        }}));
        System.out.println(result2);


    }

    private void printFromResultSet(ResultSet set) throws SQLException {
        while (set.next()) {
            System.out.println(join(" ", valueOf(set.getLong(1)), set.getString(2), set.getString(3), valueOf(set.getDate(4))));
        }
    }

}

