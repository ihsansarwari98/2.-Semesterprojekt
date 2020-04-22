package com.mycompany.creditsystem.domain.logic;

public class SystemFacade {

    public ProductionLogic productionLogic;
    public CurrentUser currentUser;
    public UserLogic userLogic;

    public SystemFacade() {
        productionLogic = new ProductionLogic();
        userLogic = new UserLogic();
        currentUser = CurrentUser.getInstance();

    }
}
