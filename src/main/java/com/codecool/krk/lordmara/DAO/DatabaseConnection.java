package com.codecool.krk.lordmara.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException{
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/table_reservation_system.db");
        }

        return connection;
    }
}
