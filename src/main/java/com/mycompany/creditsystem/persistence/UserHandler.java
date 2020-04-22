package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.logic.MD5Encryption;
import com.mycompany.creditsystem.domain.logic.Production;
import com.mycompany.creditsystem.domain.logic.User;
import org.postgresql.util.MD5Digest;

import java.security.NoSuchAlgorithmException;
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
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO users (name, username, password) VALUES (?,?,?)");
            insertStatement.setString(1, user.getName());
            insertStatement.setString(2, user.getUsername());
            insertStatement.setString(3, tempPassword);

            return insertStatement.execute();

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


    public Production getProduction(int id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM productions WHERE production_id = ?");
            statement.setInt(1, id);
            ResultSet sqlReturnValues = statement.executeQuery();

            return new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getTimestamp(3), sqlReturnValues.getInt(4));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
