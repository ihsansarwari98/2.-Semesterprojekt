package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.logic.Credit;
import com.mycompany.creditsystem.domain.interfaces.ICreditHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditHandler implements ICreditHandler {

    public static CreditHandler instance;

    public static CreditHandler getInstance() {
        if (instance == null) {
            instance = new CreditHandler();
        }
        return instance;
    }

    @Override
    public List<Credit> getCredits() {
        try{
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM credits");
            ResultSet sqlReturnvalues = statement.executeQuery();
            List<Credit> returnValue = new ArrayList<>();
            while(sqlReturnvalues.next()){
                returnValue.add(new Credit(sqlReturnvalues.getString(2)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Credit getCredit(int id) {
        try{
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM credits WHERE credit_id = ?");
            stmt.setInt(1,id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if(!sqlReturnValues.next()){
                return null;
            }
            return new Credit(sqlReturnValues.getString(2));
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createCredit(Credit credit) {
        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO credits (name) VALUES (?)");
            insertStatement.setString(1,credit.getName());

            return insertStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCreditRelation(int credit_id, int production_id) {
        try {
            PreparedStatement deleteStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_credit_role_subscriptions WHERE credit_id = ? AND production_id = ?");
            deleteStatement.setInt(1, credit_id);
            deleteStatement.setInt(2, production_id);

            return deleteStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCreditFromSystem(int id) {
        try {
            PreparedStatement deleteStatement1 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_credit_role_subscriptions WHERE production_credit_role_subscriptions.credit_id = ?");
            deleteStatement1.setInt(1, id);
            PreparedStatement deleteStatement2 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM credits WHERE credit_id = ?");
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
    public boolean updateCredit(String name, int id) {
    try{
        PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("UPDATE credits SET name = ? WHERE credit_id = ?");
        updateStatement.setString(1, name);
        updateStatement.setInt(2, id);

        return updateStatement.execute();

    } catch (SQLException ex){
        ex.printStackTrace();
        return false;
    }

    }
}
