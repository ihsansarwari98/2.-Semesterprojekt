package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IProducerHandler;
import com.mycompany.creditsystem.domain.logic.Producer;
import com.mycompany.creditsystem.domain.interfaces.IProductionCompanyHandler;
import com.mycompany.creditsystem.domain.logic.ProductionCompany;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionCompanyHandler implements IProductionCompanyHandler {
    @Override
    public ProductionCompany getProductionCompany(int id) {
        try {

            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM production_companies WHERE production_company_id = ?");
            stmt.setInt(1, id);

            ResultSet sqlReturnValue = stmt.executeQuery();
            //TODO: Tjek hvorvidt dette er måden at gøre det på
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
            // TODO: correct the parameters and ? values
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO production_companies (name, username, password) VALUES (?,?,?)");
            // TODO: get correct things

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
    public void updateProductionCompany(int id) {

    }
}