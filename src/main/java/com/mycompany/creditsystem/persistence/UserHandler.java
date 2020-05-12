package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.logic.MD5Encryption;

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
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM users");
            ResultSet sqlReturnValues = statement.executeQuery();
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

    public boolean createUser(User user) {
        MD5Encryption encryption = new MD5Encryption();
        String tempPassword = encryption.encrypt(user.getPassword());
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO users (name, username, password, access_role) VALUES (?,?,?,?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, tempPassword);
            statement.setInt(4, user.getAccessRoleInt(user.getAccessRole()));

            return statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int user_id) {
        try {
            PreparedStatement statement1 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM company_producer_relation WHERE producer_id = ?");
            PreparedStatement statement2 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_access_relation WHERE user_id = ?");
            PreparedStatement statement3 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM users WHERE user_id = ?");
            statement1.setInt(1, user_id);
            statement2.setInt(1, user_id);
            statement3.setInt(1, user_id);

            statement1.execute();
            statement2.execute();
            statement3.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public User getUserLogin(String username, String password) {
        try {
            MD5Encryption encryption = new MD5Encryption();
            String tempPassword = encryption.encrypt(password);

            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, tempPassword);

            ResultSet sqlReturnValues = statement.executeQuery();

            if (!sqlReturnValues.next()) {
                return null;
            }
                return new User(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getString(4), sqlReturnValues.getDate(5), sqlReturnValues.getInt(6));

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getUsersFromAccessRole(int accessRole) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM users WHERE access_role = ?");
            statement.setInt(1, accessRole);
            ResultSet sqlReturnValues = statement.executeQuery();
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
