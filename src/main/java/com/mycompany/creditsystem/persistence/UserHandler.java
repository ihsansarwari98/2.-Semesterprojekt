package com.mycompany.creditsystem.persistence;

import java.security.MessageDigest;
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

    public User getUser(String name) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM users WHERE name = ?");
            statement.setString(1, name);
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

    public User getUser(int user_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM users WHERE user_id = ?");
            statement.setInt(1, user_id);
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


    public String encrypt(String password) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean createUser(User user) {
        String tempPassword = encrypt(user.getPassword());
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
            User userToDelete = getUser(user_id);
            PreparedStatement statement1 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM company_producer_relation WHERE producer_id = ?");

            if (userToDelete.getAccessRoleInt() == 2) {
                statement1 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM company_producer_relation WHERE production_company_id = ?");
                for (int i = 0; i < getProducersLinkedToProductionCompany(userToDelete.getId()).size(); i++) {
                    deleteUser(getProducersLinkedToProductionCompany(userToDelete.getId()).get(i).getId());
                }
            }

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
            String tempPassword = encrypt(password);

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

    public ArrayList<User> getUsersFromAccessRole(int accessRole, String name) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM users WHERE access_role = ? AND name ~* ?");
            statement.setInt(1, accessRole);
            statement.setString(2, name);
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

    public ArrayList<User> getProducersLinkedToProductionCompany(int production_company_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT users.user_id, users.name, users.username, users.password, users.date_created, users.access_role FROM users, company_producer_relation WHERE users.access_role = 1 AND company_producer_relation.production_company_id = ? AND users.user_id = company_producer_relation.producer_id");
            statement.setInt(1, production_company_id);
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

    public boolean linkProducerToCompany(int producer_id, int company_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO company_producer_relation (producer_id, production_company_id) VALUES (?, ?)");
            statement.setInt(1, producer_id);
            statement.setInt(2, company_id);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
