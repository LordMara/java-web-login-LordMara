package com.codecool.krk.lordmara.DAO;

import com.codecool.krk.lordmara.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final UserDAO INSTANCE = new UserDAO();

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    public User getUser(String enteredLogin, String enteredPassword) throws SQLException {
        User user = null;

        String query = "SELECT id, name, surname FROM user WHERE login = ? AND password = ?";

        PreparedStatement pstmt = DatabaseConnection.getConnection().prepareStatement(query);
        pstmt.setString(1, enteredLogin);
        pstmt.setString(2, enteredPassword);

        ResultSet result = pstmt.executeQuery();

        while (result.next()) {
            Integer id = result.getInt("id");
            String name = result.getString("name");
            String surname = result.getString("surname");

            user = new User(id, name, surname, enteredLogin, enteredPassword);
        }

        result.close();
        pstmt.close();

        return user;
    }
}
