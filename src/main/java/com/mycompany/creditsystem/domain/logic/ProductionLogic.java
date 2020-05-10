package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.Production;
import com.mycompany.creditsystem.persistence.ProductionHandler;
import com.mycompany.creditsystem.persistence.User;

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

    public ArrayList<Production> getProductionsLinkedToProducer(int producer_id) {
        return ProductionHandler.getInstance().getProductionsLinkedToProducer(producer_id);
    }

    public Production getProduction(String title) {
        return ProductionHandler.getInstance().getProduction(title);
    }

    public boolean linkProductionToProducer(int production_id, int producer_id) {
        return ProductionHandler.getInstance().linkProductionToProducer(production_id, producer_id);
    }

    public ArrayList<Production> getProductionsLinkedToProductionCompany(int production_company_id) {
        return ProductionHandler.getInstance().getProductionsLinkedToProductionCompany(production_company_id);
    }

    public boolean linkProductionToProductionCompany(int production_id, int production_company_id) {
        return ProductionHandler.getInstance().linkProductionToProductionCompany(production_id, production_company_id);
    }

    public boolean addCreditAndRoleToProduction(int production_id, int credit_id, int role_id) {
        return ProductionHandler.getInstance().addCreditAndRoleToProduction(production_id, credit_id, role_id);
    }
    public ArrayList<User> getProducersLinkedToProduction(int production_id) {
        return ProductionHandler.getInstance().getProducersLinkedToProduction(production_id);
    }

    public boolean isProductionLinkedToProducer (int production_id, int producer_id) {
        ArrayList<Production> temp = ProductionHandler.getInstance().getProductionsLinkedToProducer(producer_id);
        boolean foundProduction = false;
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getId() == production_id) {
                foundProduction = true;
            }
        }
        return foundProduction;
    }

    public boolean isProductionLinkedToProductionCompany (int production_id, int user_id) {
        ArrayList<Production> temp = ProductionHandler.getInstance().getProductionsLinkedToProductionCompany(user_id);
        boolean foundProduction = false;
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getId() == production_id) {
                foundProduction = true;
            }
        }
        return foundProduction;
    }

}
