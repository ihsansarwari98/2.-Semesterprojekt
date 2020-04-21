package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IProductionHandler;
import com.mycompany.creditsystem.domain.logic.Production;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionHandler implements IProductionHandler {

    public static ProductionHandler instance;

    public ProductionHandler() {
        ConnectionHandler.getInstance();
    }

    public static ProductionHandler getInstance() {
        if (instance == null) {
            instance = new ProductionHandler();
        }
        return instance;
    }

    @Override
    public List<Production> getProductions() {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM productions");
            ResultSet sqlReturnvalues = statement.executeQuery();
            List<Production> returnValue = new ArrayList<>();
            while (sqlReturnvalues.next()) {
                returnValue.add(new Production(sqlReturnvalues.getString(2)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Production getProduction(int id) {
        try {
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM productions WHERE production_id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Production(sqlReturnValues.getString(2));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createProduction(Production production) {
        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO productions (title, deadline, status) VALUES (?,?,?)");
            insertStatement.setString(1, production.getTitle());
            insertStatement.setDate(1, production.getDeadline());
            insertStatement.setInt(1, production.getStatusInt());

            return insertStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduction(int id) {
        try {
            PreparedStatement deleteStatement1 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_credit_role_subscriptions WHERE production_id = ?");
            deleteStatement1.setInt(1, id);
            PreparedStatement deleteStatement2 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM productions WHERE production_id = ?");
            deleteStatement2.setInt(1, id);

            deleteStatement1.execute();
            deleteStatement2.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProduction(Production production) {
        /*
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("UPDATE credits SET name = ? WHERE credit_id = ?");
            updateStatement.setString(1, production.getTitle());
            updateStatement.setInt(2, production.getStatusInt());

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
            }

         */
        return false;
    }
}
