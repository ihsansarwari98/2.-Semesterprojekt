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
    public String state;

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

    public void updateMyLists() {
        currentUser.setMyProductions(productionLogic.getProductionsLinkedToUser(currentUser.getUser().getId()));
        currentUser.setMyProducers(userLogic.getProducersLinkedToProductionCompany(currentUser.getUser().getId()));
        currentUser.setMyProductionCompanies(userLogic.getProductionCompanies());
        if (currentUser.getSortedByStatus().equals("name")) {
            currentUser.sortListsByName();
        } else if (currentUser.getSortedByStatus().equals("nameReversed")) {
            currentUser.sortListsByNameReversed();
        } else if (currentUser.getSortedByStatus().equals("deadline")) {
            currentUser.sortListsByName();
            currentUser.sortListsByDeadline();
        } else if (currentUser.getSortedByStatus().equals("deadlineReversed")) {
            currentUser.sortListsByName();
            currentUser.sortListsByDeadlineReversed();
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
