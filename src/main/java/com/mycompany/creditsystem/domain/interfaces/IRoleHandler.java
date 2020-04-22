package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Credit;
import com.mycompany.creditsystem.domain.logic.Role;

import java.util.List;

public interface IRoleHandler {
    public List<Role> getRoles();
    public Role getRole(int id);
    public boolean createRole (Role Role);
    public boolean deleteRole (int id);
    public boolean updateRole (Role role);
}
