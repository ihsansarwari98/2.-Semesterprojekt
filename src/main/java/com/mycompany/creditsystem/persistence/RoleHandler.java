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
    @Override
    public List<Role> getRoles() {
        try{
            PreparedStatement statement = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM roles");
            ResultSet sqlReturnvalues = statement.executeQuery();
            List<Role> returnValue = new ArrayList<>();
            while(sqlReturnvalues.next()){
                returnValue.add(new Role(sqlReturnvalues.getString(1) ));
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
            PreparedStatement stmt = ConnectionHandler.getInstance().getConnection().prepareStatement("SELECT * FROM roles WHERE role_id = ?");
            stmt.setInt(1,id);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if(!sqlReturnValues.next()){
                return null;
            }
            return new Role(sqlReturnValues.getString(1));
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean createRole(Role role) {
        try {
            PreparedStatement insertStatement = ConnectionHandler.getInstance().getConnection().prepareStatement("INSERT INTO roles (role_title) " +
                    "+ VALUES (?)");
            insertStatement.setString(1,role.getTitle());

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
            updateStatement.setString(1,role.getTitle());
            updateStatement.setInt(2,role.getId());

            return updateStatement.execute();

        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
