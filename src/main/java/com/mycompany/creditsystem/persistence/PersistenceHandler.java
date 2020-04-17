package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.logic.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceHandler {
    private static PersistenceHandler instance;
    private static String url = "balarama.db.elephantsql.com";
    private static int port = 5432;
    private static String databaseName = "fhufzrfh";
    private static String username = "fhufzrfh";
    private static String password = "fleSZCM385yds330ePT0jVCOCdZhctJq";
    private static Connection connection = null;

    private PersistenceHandler() {
        initializePostgresqlDatabase();
    }

    public static PersistenceHandler getInstance() {
        if (instance == null) {
            instance = new PersistenceHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }


    public static List<User> getUsers() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet sqlReturnValues = stmt.executeQuery();
            int rowcount = 0;
            List<User> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                // TODO: fix so the database and user contructor is equal
                //returnValue.add(new User(sqlReturnValues.getString(1), sqlReturnValues.getString(2)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean createUser(User user) {
        try {
            // TODO: correct the parameters and ? values
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (name, accessRole) VALUES (?,?)");
            // TODO: get correct things
            /*
            insertStatement.setString(1, user.getName());
            insertStatement.setString(2, user.getAccessRole());

             */
            insertStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}