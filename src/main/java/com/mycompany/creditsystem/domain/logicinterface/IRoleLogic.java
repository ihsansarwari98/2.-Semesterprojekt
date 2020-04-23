package com.mycompany.creditsystem.domain.logicinterface;

import com.mycompany.creditsystem.domain.logic.Role;

import java.util.ArrayList;

public interface IRoleLogic {
    public ArrayList<Role> getRoles();
    public Role getRole(int id);
    public boolean createRole (Role Role);
    public boolean deleteRole (int id);
    public boolean updateRole (Role role);
    public Role getRoleFromCredit(int production_id, int credit_id);
}
