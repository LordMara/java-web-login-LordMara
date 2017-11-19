package com.codecool.krk.lordmara.DAO;

import com.codecool.krk.lordmara.model.User;

import java.sql.*;

public class UserDAO {
    private static final UserDAO INSTANCE = new UserDAO();

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    public User getUser(String enteredUserName, String enteredPassword) {
        User user = null;

        try (Connection con = DatabaseConnection.getConnection();
        PreparedStatement ps = createPreparedStatement(con, enteredUserName, enteredPassword);
        ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                String surname = result.getString("surname");

                user = new User(id, name, surname, enteredUserName, enteredPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return user;
    }

    private PreparedStatement createPreparedStatement(Connection con, String enteredUserName, String enteredPassword) throws SQLException {
        String query = "SELECT id, name, surname FROM user WHERE user_name = ? AND password = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, enteredUserName);
        ps.setString(2, enteredPassword);

        return ps;
    }
}
