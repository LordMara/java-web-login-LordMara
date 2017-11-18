package com.codecool.krk.lordmara.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/database.db");
        return connection;
    }
}
