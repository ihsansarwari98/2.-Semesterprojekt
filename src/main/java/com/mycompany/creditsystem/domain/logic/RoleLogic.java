package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.domain.logicinterface.IRoleLogic;
import com.mycompany.creditsystem.persistence.Role;
import com.mycompany.creditsystem.persistence.RoleHandler;

import java.util.ArrayList;

public class RoleLogic implements IRoleLogic {


    @Override
    public ArrayList<Role> getRoles() {
        return RoleHandler.getInstance().getRoles();
    }

    @Override
    public Role getRole(int id) {
        return RoleHandler.getInstance().getRole(id);
    }

    @Override
    public boolean createRole(Role role) {
        return RoleHandler.getInstance().createRole(role);
    }

    @Override
    public boolean deleteRole(int id) {
        return RoleHandler.getInstance().deleteRole(id);
    }

    @Override
    public boolean updateRole(Role role) {
        return RoleHandler.getInstance().updateRole(role);
    }

    @Override
    public Role getRoleFromCredit(int production_id, int credit_id) {
        return RoleHandler.getInstance().getRoleFromCredit(production_id, credit_id);
    }
}
