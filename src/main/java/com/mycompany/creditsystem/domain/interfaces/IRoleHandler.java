package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.persistence.Role;

import java.util.ArrayList;

public interface IRoleHandler {
    public ArrayList<Role> getRoles();
    public Role getRole(int id);
    public boolean createRole (Role Role);
    public boolean deleteRole (int id);
    public boolean updateRole (Role role);
    public Role getRoleFromCredit(int production_id, int credit_id);
}
