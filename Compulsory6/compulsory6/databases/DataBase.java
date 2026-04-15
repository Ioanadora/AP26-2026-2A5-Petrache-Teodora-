package com.lab6.compulsory6.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static final String URL = "jdbc:sqlite:movies.db";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException exception) {
            System.err.println(exception);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            System.err.println(exception);
        }
    }
}