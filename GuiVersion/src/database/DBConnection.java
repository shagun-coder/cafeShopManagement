package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cafe_shop",
                    "root",
                    "");

            System.out.println("Database Connected!");

            return connect;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}