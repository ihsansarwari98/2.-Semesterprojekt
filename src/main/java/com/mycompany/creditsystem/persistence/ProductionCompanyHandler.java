package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IProductionCompanyHandler;
import com.mycompany.creditsystem.domain.logic.ProductionCompany;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionCompanyHandler implements IProductionCompanyHandler {

    public static ProductionCompanyHandler instance;

    public static ProductionCompanyHandler getInstance() {
        if (instance == null) {
            instance = new ProductionCompanyHandler();
        }
        return instance;
    }

    @Override
    public ProductionCompany getProductionCompany(int id) {
        try {
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM production_companies WHERE production_company_id = ?");
            stmt.setInt(1, id);

            ResultSet sqlReturnValue = stmt.executeQuery();
            if (!sqlReturnValue.next()) {
                return null;
            }
            return new ProductionCompany(sqlReturnValue.getString(1), sqlReturnValue.getString(2), sqlReturnValue.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createProductionCompany(ProductionCompany productionCompany) {

        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO production_companies (name, username, password) VALUES (?,?,?)");

            insertStatement.setString(1, productionCompany.getName());
            insertStatement.setString(2, productionCompany.getUsername());
            insertStatement.setString(3, productionCompany.getPassword());

            insertStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteProductionCompany(int id) {
        try {
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM production_companies WHERE production_company_id = ?");
            stmt.setInt(1,id);
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProductionCompany(String name, int production_company_id) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("UPDATE production_companies SET name = ? WHERE production_company_id = ?");
            updateStatement.setString(1, name);
            updateStatement.setInt(2, production_company_id);

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean linkProducerToProductionCompany(int producer_id, int production_company_id) {
        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO company_producer_subscriptions (producer_id, production_company_id) VALUES (?,?)");

            insertStatement.setInt(1, producer_id);
            insertStatement.setInt(2, production_company_id);

            insertStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeProducerFromProductionCompany(int producer_id, int production_company_id) {
        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM company_producer_subscriptions WHERE producer_id = ? AND production_company_id = ?");

            insertStatement.setInt(1, producer_id);
            insertStatement.setInt(2, production_company_id);

            insertStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}