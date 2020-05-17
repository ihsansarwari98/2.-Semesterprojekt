package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.Production;
import com.mycompany.creditsystem.persistence.ProductionHandler;
import com.mycompany.creditsystem.persistence.User;
import com.mycompany.creditsystem.presentation.PrimaryController;

import java.util.ArrayList;
import java.util.Date;

public class ProductionLogic {

    public ArrayList<Production> getProductions(String titlePart) {
        return ProductionHandler.getInstance().getProductions(titlePart);
    }

    public ArrayList<Production> getProductions() {
        return ProductionHandler.getInstance().getProductions();
    }

    public Production getProduction(int id) {
        return ProductionHandler.getInstance().getProduction(id);
    }

    public boolean createProduction(Production production) {
        return ProductionHandler.getInstance().createProduction(production);
    }

    public boolean createProduction(String prodname) {
        Production production = new Production(prodname);
        return ProductionHandler.getInstance().createProduction(production);
    }

    public boolean deleteProduction(int id) {
        return ProductionHandler.getInstance().deleteProduction(id);
    }

    public boolean updateProductionTitle(String title, int production_id) {
        return ProductionHandler.getInstance().updateProductionTitle(title, production_id);
    }

    public boolean updateProductionDeadline(Date deadline, int production_id) {
        return ProductionHandler.getInstance().updateProductionDeadline(deadline, production_id);
    }

    public boolean updateProductionStatus(int status, int production_id) {
        return ProductionHandler.getInstance().updateProductionStatus(status, production_id);
    }

    public ArrayList<Production> getProductionsLinkedToUser(int user_id) {
        return ProductionHandler.getInstance().getProductionsLinkedToUser(user_id);
    }

    public Production getProduction(String title) {
        return ProductionHandler.getInstance().getProduction(title);
    }

    public boolean linkProductionToUser(int production_id, int user_id) {
        return ProductionHandler.getInstance().linkProductionToUser(production_id, user_id);
    }

    public boolean addCreditAndRoleToProduction(int production_id, int credit_id, int role_id) {
        return ProductionHandler.getInstance().addCreditAndRoleToProduction(production_id, credit_id, role_id);
    }
    public ArrayList<User> getProducersLinkedToProduction(int production_id) {
        return ProductionHandler.getInstance().getUsersLinkedToProduction(production_id);
    }

    public boolean isProductionLinkedToUser (int production_id, int user_id) {
        ArrayList<Production> temp = ProductionHandler.getInstance().getProductionsLinkedToUser(user_id);
        boolean foundProduction = false;
        for (Production production : temp) {
            if (production.getId() == production_id) {
                foundProduction = true;
                break;
            }
        }
        return foundProduction;
    }
}
