package com.mycompany.creditsystem.persistence;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;

public class ProductionHandler {

    public static ProductionHandler instance;

    public static ProductionHandler getInstance() {
        if (instance == null) {
            instance = new ProductionHandler();
        }
        return instance;
    }

    private Timestamp convertDateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public ArrayList<Production> getProductions() {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM productions");
            ResultSet sqlReturnValues = statement.executeQuery();
            ArrayList<Production> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                returnValue.add(new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getTimestamp(3), sqlReturnValues.getInt(4)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Production> getProductions(String titlePart) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM productions WHERE title ~* ?");
            statement.setString(1, titlePart);
            ResultSet sqlReturnValues = statement.executeQuery();
            ArrayList<Production> returnValue = new ArrayList<>();
            int iterator = 0;
            while (sqlReturnValues.next() && iterator < 5) {
                iterator++;
                returnValue.add(new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getTimestamp(3), sqlReturnValues.getInt(4)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Production getProduction(String title) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM productions WHERE title = ?");
            statement.setString(1, title);
            ResultSet sqlReturnValues = statement.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getTimestamp(3), sqlReturnValues.getInt(4));
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
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getTimestamp(3), sqlReturnValues.getInt(4));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean createProduction(Production production) {
        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO productions (title, deadline, status) VALUES (?,?,?)");
            insertStatement.setString(1, production.getTitle());
            insertStatement.setTimestamp(2, convertDateToTimestamp(production.getDeadline()));
            insertStatement.setInt(3, production.getStatusInt());

            return insertStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduction(int id) {
        try {
            PreparedStatement deleteStatement1 = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_credit_role_relation WHERE production_id = ?");
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

    public boolean updateProductionTitle(String title, int production_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("UPDATE productions SET title = ? WHERE production_id = ?");
            updateStatement.setString(1, title);
            updateStatement.setInt(2, production_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateProductionDeadline(Date deadline, int production_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("UPDATE productions SET deadline = ? WHERE production_id = ?");
            updateStatement.setTimestamp(1, convertDateToTimestamp(deadline));
            updateStatement.setInt(2, production_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateProductionStatus(int status, int production_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("UPDATE productions SET status = ? WHERE production_id = ?");
            updateStatement.setInt(1, status);
            updateStatement.setInt(2, production_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean addCreditAndRoleToProduction(int production_id, int credit_id, int role_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO production_credit_role_relation (production_id, credit_id, role_id) VALUES (?,?,?)");
            updateStatement.setInt(1, production_id);
            updateStatement.setInt(2, credit_id);
            updateStatement.setInt(3, role_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean linkProductionToUser(int production_id, int user_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO production_access_relation (production_id, user_id) VALUES (?,?)");
            updateStatement.setInt(1, production_id);
            updateStatement.setInt(2, user_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean unlinkProductionFromUser(int production_id, int user_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_access_relation WHERE production_id = ? AND user_id = ?");
            updateStatement.setInt(1, production_id);
            updateStatement.setInt(2, user_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Production> getProductionsLinkedToUser(int user_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT productions.production_id, productions.title, productions.deadline, productions.status  FROM production_access_relation, users, productions WHERE users.user_id = production_access_relation.user_id AND production_access_relation.user_id = ? AND users.user_id = production_access_relation.user_id AND production_access_relation.production_id = productions.production_id");
            statement.setInt(1, user_id);
            ResultSet sqlReturnValues = statement.executeQuery();
            ArrayList<Production> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                returnValue.add(new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getTimestamp(3), sqlReturnValues.getInt(4)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getUsersLinkedToProduction(int production_id){
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT  users.user_id, users.name  FROM users, production_access_relation, productions WHERE productions.production_id = production_access_relation.production_id AND production_id = ? AND production_access_relation.user_id = users.user_id");
            statement.setInt(1,production_id);
            ResultSet sqlReturnValues = statement.executeQuery();
            ArrayList<User> returnValue = new ArrayList<>();
            while(sqlReturnValues.next()){
                returnValue.add(new User(sqlReturnValues.getInt(1),sqlReturnValues.getString(2)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
