package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.Role;
import com.mycompany.creditsystem.persistence.RoleHandler;

import java.util.ArrayList;

public class RoleLogic {

    public ArrayList<Role> getRoles() {
        return RoleHandler.getInstance().getRoles();
    }

    public Role getRole(int id) {
        return RoleHandler.getInstance().getRole(id);
    }

    public boolean createRole(Role role) {
        return RoleHandler.getInstance().createRole(role);
    }

    public boolean deleteRole(int id) {
        return RoleHandler.getInstance().deleteRole(id);
    }

    public boolean updateRole(Role role) {
        return RoleHandler.getInstance().updateRole(role);
    }

    public Role getRoleFromCredit(int production_id, int credit_id) {
        return RoleHandler.getInstance().getRoleFromCredit(production_id, credit_id);
    }
}
