package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.RoleHandler;

public class SystemFacade {

    public ProductionLogic productionLogic;
    public CurrentUser currentUser;
    public UserLogic userLogic;
    public CreditLogic creditLogic;
    public RoleHandler roleHandler;

    public SystemFacade() {
        productionLogic = new ProductionLogic();
        userLogic = new UserLogic();
        creditLogic = new CreditLogic();
        roleHandler = new RoleHandler();
        currentUser = CurrentUser.getInstance();

    }
}
