package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IAdministratorHandler;
import com.mycompany.creditsystem.domain.logic.Administrator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorHandler implements IAdministratorHandler {


    @Override
    public Administrator getAdministrator(int id) {
        try {
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM administrators WHERE administrator_id = ?");
            stmt.setInt(1, id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Administrator(sqlReturnValues.getString("name"), "username", "password");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createAdministrator(Administrator administrator) {
        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO administrators (name, username, password) VALUES (?,?,?)");

            insertStatement.setString(1, administrator.getName());
            insertStatement.setString(2, administrator.getUsername());
            insertStatement.setString(3, administrator.getPassword());

            insertStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteAdministrator(int id) {
        try {
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM administrators WHERE administrator_id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void updateAdministrator(int id) {

    }
}
