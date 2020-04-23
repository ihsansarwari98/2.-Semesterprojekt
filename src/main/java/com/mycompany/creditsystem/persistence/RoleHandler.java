package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IRoleHandler;
import com.mycompany.creditsystem.domain.logic.Credit;
import com.mycompany.creditsystem.domain.logic.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleHandler implements IRoleHandler {

    public static RoleHandler instance;

    public static RoleHandler getInstance() {
        if (instance == null) {
            instance = new RoleHandler();
        }
        return instance;
    }


    @Override
    public ArrayList<Role> getRoles() {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM roles");
            ResultSet sqlReturnvalues = statement.executeQuery();
            ArrayList<Role> returnValue = new ArrayList<>();
            while (sqlReturnvalues.next()) {
                returnValue.add(new Role(sqlReturnvalues.getString(1)));
            }
            return returnValue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Role getRole(int id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM roles WHERE role_id = ?");
            statement.setInt(1, id);
            ResultSet sqlReturnValues = statement.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Role(sqlReturnValues.getString(1));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Role getRoleFromCredit(int production_id, int credit_id) {
        try {
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT roles.role_id, role_title FROM production_credit_role_subscriptions, roles WHERE production_credit_role_subscriptions.role_id = roles.role_id AND production_id = ? AND credit_id = ?");
            statement.setInt(1, production_id);
            statement.setInt(2, credit_id);
            ResultSet sqlReturnValues = statement.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Role(sqlReturnValues.getInt(1), sqlReturnValues.getString(2));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createRole(Role role) {
        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO roles (role_title) " +
                    "+ VALUES (?)");
            insertStatement.setString(1, role.getTitle());

            return insertStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRole(int id) {
        try {

            PreparedStatement deleteStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("DELETE FROM roles WHERE role_id =?");
            deleteStatement.setInt(1, id);

            return deleteStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRole(Role role) {
        try {
            PreparedStatement updateStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("UPDATE roles SET role_title=?, WHERE role_id =?");
            updateStatement.setString(1, role.getTitle());
            updateStatement.setInt(2, role.getId());

            return updateStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
