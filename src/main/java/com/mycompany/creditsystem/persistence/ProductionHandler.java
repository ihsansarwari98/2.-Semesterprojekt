package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IProductionHandler;
import com.mycompany.creditsystem.domain.logic.Production;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;

public class ProductionHandler implements IProductionHandler {

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

    @Override
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

    @Override
    public ArrayList<Production> getProductions(String titlePart) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM productions WHERE title ~* ?");
            statement.setString(1, titlePart);
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

    @Override
    public Production getProduction(int id) {
        try {
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM productions WHERE production_id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Production(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getTimestamp(3), sqlReturnValues.getInt(4));
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
            insertStatement.setTimestamp(2, convertDateToTimestamp(production.getDeadline()));
            insertStatement.setInt(3, production.getStatusInt());

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


    @Override
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

    @Override
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
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO production_credit_role_subscriptions (production_id, credit_id, role_id) VALUES (?,?,?)");
            updateStatement.setInt(1, production_id);
            updateStatement.setInt(2, credit_id);
            updateStatement.setInt(3, role_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean linkProductionToProductionCompany(int production_id, int production_company_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO company_production_subscriptions (production_id, production_company_id) VALUES (?,?)");
            updateStatement.setInt(1, production_id);
            updateStatement.setInt(2, production_company_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean linkProductionToProducer(int production_id, int producer_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO producer_production_subscriptions (production_id, producer_id) VALUES (?,?)");
            updateStatement.setInt(1, production_id);
            updateStatement.setInt(2, producer_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Production> getProductionsLinkedToProducer(int producer_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT productions.production_id, productions.title, productions.deadline, productions.status  FROM producer_production_subscriptions,users, productions WHERE producer_production_subscriptions.producer_id = users.user_id AND producer_id = ? AND producer_production_subscriptions.production_id = productions.production_id");
            statement.setInt(1, producer_id);
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
}
