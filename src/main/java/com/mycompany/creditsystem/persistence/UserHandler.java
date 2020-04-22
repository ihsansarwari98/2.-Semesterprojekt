package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.logic.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserHandler {

    public static UserHandler instance;

    public static UserHandler getInstance() {
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        try {
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM users");
            ResultSet sqlReturnValues = stmt.executeQuery();
            ArrayList<User> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                returnValue.add(new User(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getString(4), sqlReturnValues.getDate(5), sqlReturnValues.getInt(6)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
