package com.codecool.krk.lordmara.DAO;

import com.codecool.krk.lordmara.model.User;

import java.sql.*;

public class UserDAO {
    private static final UserDAO INSTANCE = new UserDAO();

    private final String PATH = "jdbc:sqlite:src/main/resources/db/table_reservation_system.db";

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    public User getUser(String enteredLogin, String enteredPassword) throws SQLException {
        User user = null;

        try (Connection con = DriverManager.getConnection(this.PATH);
        PreparedStatement ps = createPreparedStatement(con, enteredLogin, enteredPassword);
        ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");

                user = new User(id, name, surname, enteredLogin, enteredPassword);
            }
        }
        return user;
    }

    private PreparedStatement createPreparedStatement(Connection con, String enteredLogin, String enteredPassword) throws SQLException {
        String query = "SELECT id, name, surname FROM user WHERE login = ? AND password = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, enteredLogin);
        ps.setString(2, enteredPassword);

        return ps;
    }
}
