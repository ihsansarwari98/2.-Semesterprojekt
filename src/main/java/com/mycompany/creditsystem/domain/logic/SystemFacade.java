package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.Production;
import com.mycompany.creditsystem.persistence.RoleHandler;

public class SystemFacade {

    public ProductionLogic productionLogic;
    public CurrentUser currentUser;
    public UserLogic userLogic;
    public CreditLogic creditLogic;
    public RoleLogic roleLogic;
    public Production activeProduction;

    public SystemFacade() {
        productionLogic = new ProductionLogic();
        userLogic = new UserLogic();
        creditLogic = new CreditLogic();
        roleLogic = new RoleLogic();
        currentUser = CurrentUser.getInstance();
        activeProduction = null;
    }

    public void setActiveProduction(Production activeProduction) {
        this.activeProduction = activeProduction;
    }

    public Production getActiveProduction() {
        return activeProduction;
    }
}
