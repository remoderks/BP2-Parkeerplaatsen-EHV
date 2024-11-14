package com.bp2parkeerplaatsenehv.controllers.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Beroepsproduct2";
    private static final String USER = "root";
    private static final String PASS = "Avans";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}

