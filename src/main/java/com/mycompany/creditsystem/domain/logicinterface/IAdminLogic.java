package com.mycompany.creditsystem.domain.logicinterface;

import com.mycompany.creditsystem.domain.logic.Administrator;

public interface IAdminLogic {
    public Administrator getAdministrator(int id);
    public boolean createAdministrator(Administrator administrator);
    public boolean deleteAdministrator(int id);
    public void updateAdministrator(int id);

}
