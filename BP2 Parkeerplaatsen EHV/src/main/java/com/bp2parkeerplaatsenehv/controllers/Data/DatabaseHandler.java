package com.bp2parkeerplaatsenehv.controllers.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ReserveringsappEHV";
    private static final String USER = "ReserveringsappEHV";
    private static final String PASS = "Avans123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}