package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IProducerHandler;
import com.mycompany.creditsystem.domain.logic.Producer;
import com.mycompany.creditsystem.domain.logic.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerHandler implements IProducerHandler {

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

    //Har fjernet static tilstand!
    @Override
    public boolean createProducer(Producer producer) {
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

    @Override
    public Producer getProducer(int id) {
        try {

            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM producers WHERE producer_id = ?");
            stmt.setInt(1, id);

            ResultSet sqlReturnValue = stmt.executeQuery();
            //TODO: Tjek hvorvidt dette er måden at gøre det på
            if (!sqlReturnValue.next()) {
                return null;
            }
            return new Producer(sqlReturnValue.getString(1), sqlReturnValue.getString(2), sqlReturnValue.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteProducer(int id) {

    }

    @Override
    public void updateProducer(int id) {

    }
}
