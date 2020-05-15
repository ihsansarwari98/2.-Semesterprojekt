package com.mycompany.creditsystem.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {

        private static ConnectionHandler instance;
        private static String url = "balarama.db.elephantsql.com";
        private static int port = 5432;
        private static String databaseName = "fhufzrfh";
        private static String username = "fhufzrfh";
        private static String password = "fleSZCM385yds330ePT0jVCOCdZhctJq";
        private static Connection connection = null;

        private ConnectionHandler() {
            createConnection();
        }

        public static ConnectionHandler getInstance() {
            if (instance == null) {
                instance = new ConnectionHandler();
            }
            return instance;
        }

        private void createConnection() {
            try {
                DriverManager.registerDriver(new org.postgresql.Driver());
                connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            } catch (SQLException | IllegalArgumentException ex) {
                System.out.println("Error while loading database, check your connection or wait a few minutes.");
                ex.printStackTrace(System.err);
            }
            finally {
                if (connection == null) System.exit(-1);
            }
        }

        public Connection getConnection() {
            return connection;
        }
}
