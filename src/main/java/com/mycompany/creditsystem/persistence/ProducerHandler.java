package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.Producer;
import com.mycompany.creditsystem.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerHandler {

    public static List<User> getProducers() {
        try {
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM producers");
            ResultSet sqlReturnValues = stmt.executeQuery();
            int rowcount = 0;
            List<User> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                // TODO: fix so the database and user contructor is equal
                returnValue.add(new Producer(sqlReturnValues.getString(1), sqlReturnValues.getString(2), sqlReturnValues.getString(3)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean createProducer(Producer producer) {
        try {
            // TODO: correct the parameters and ? values
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO producers (name, username, password) VALUES (?,?,?)");
            // TODO: get correct things

            insertStatement.setString(1, producer.getName());
            insertStatement.setString(2, producer.getUsername());
            insertStatement.setString(3, producer.getPassword());


            insertStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
