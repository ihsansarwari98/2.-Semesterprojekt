package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.logic.Credit;
import com.mycompany.creditsystem.domain.interfaces.ICreditHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditHandler implements ICreditHandler {



    @Override
    public List<Credit> getCredits() {

        try{
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM credits");
            ResultSet sqlReturnvalues = statement.executeQuery();
            List<Credit> returnValue = new ArrayList<>();
            while(sqlReturnvalues.next()){
                returnValue.add(new Credit(sqlReturnvalues.getString(1),sqlReturnvalues.getString(2)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Credit getCredit(int id) {
        return null;
    }

    @Override
    public void createCredit(Credit Credit) {

    }

    @Override
    public void deleteCredit(int id) {

    }

    @Override
    public void updateCredit(int id) {

    }
}
