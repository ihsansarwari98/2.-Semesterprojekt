package com.mycompany.creditsystem.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditHandler {

    public static CreditHandler instance;

    public static CreditHandler getInstance() {
        if (instance == null) {
            instance = new CreditHandler();
        }
        return instance;
    }

    public ArrayList<Credit> getCredits() {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM credits");
            ResultSet sqlReturnvalues = statement.executeQuery();
            ArrayList<Credit> returnValue = new ArrayList<>();
            while (sqlReturnvalues.next()) {
                returnValue.add(new Credit(sqlReturnvalues.getInt(1), sqlReturnvalues.getString(2)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Credit> getCredits(int production_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT credits.credit_id, credits.name FROM production_credit_role_relation, credits, productions  WHERE production_credit_role_relation.production_id = ? AND credits.credit_id = production_credit_role_relation.credit_id AND productions.production_id = production_credit_role_relation.production_id");
            statement.setInt(1, production_id);
            ResultSet sqlReturnValues = statement.executeQuery();
            ArrayList<Credit> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                returnValue.add(new Credit(sqlReturnValues.getInt(1), sqlReturnValues.getString(2)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<CreditWithRole> getCreditWithRole(int production_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT credits.credit_id, credits.name, roles.role_id, roles.role_title FROM production_credit_role_relation, credits, productions, roles  WHERE production_credit_role_relation.production_id = ? AND credits.credit_id = production_credit_role_relation.credit_id AND productions.production_id = production_credit_role_relation.production_id AND production_credit_role_relation.role_id = roles.role_id");
            statement.setInt(1, production_id);
            ResultSet sqlReturnValues = statement.executeQuery();
            ArrayList<CreditWithRole> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                returnValue.add(new CreditWithRole(new Credit(sqlReturnValues.getInt(1), sqlReturnValues.getString(2)), new Role (sqlReturnValues.getInt(3), sqlReturnValues.getString(4))));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public boolean removeAllCreditsFromProduction(int production_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_credit_role_relation WHERE production_credit_role_relation.production_id = ?");
            statement.setInt(1, production_id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Credit> getCredits(String namePart) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM credits WHERE name ~* ?");
            statement.setString(1, namePart);
            ResultSet sqlReturnValues = statement.executeQuery();
            ArrayList<Credit> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                returnValue.add(new Credit(sqlReturnValues.getInt(1), sqlReturnValues.getString(2)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Credit getCredit(int id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM credits WHERE credit_id = ?");
            statement.setInt(1, id);
            ResultSet sqlReturnValues = statement.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Credit(sqlReturnValues.getString(2));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean createCredit(Credit credit) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO credits (name) VALUES (?)");
            statement.setString(1, credit.getName());

            return statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteCreditRelation(int credit_id, int production_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_credit_role_relation WHERE credit_id = ? AND production_id = ?");
            statement.setInt(1, credit_id);
            statement.setInt(2, production_id);

            return statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteCreditFromSystem(int id) {
        try {
            PreparedStatement statement1 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_credit_role_relation WHERE production_credit_role_relation.credit_id = ?");
            statement1.setInt(1, id);
            PreparedStatement statement2 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM credits WHERE credit_id = ?");
            statement2.setInt(1, id);

            statement1.execute();
            statement2.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addCreditRelation(int production_id, int credit_id, int role_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO production_credit_role_relation (production_id, credit_id, role_id) VALUES (?,?,?)");
            statement.setInt(1, production_id);
            statement.setInt(2, credit_id);
            statement.setInt(3, role_id);

            return statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateCreditName(String name, int id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("UPDATE credits SET name = ? WHERE credit_id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);

            return statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
