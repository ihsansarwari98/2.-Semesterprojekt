package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.domain.logicinterface.IProductionLogic;
import com.mycompany.creditsystem.persistence.ProductionHandler;

import java.util.ArrayList;
import java.util.Date;

public class ProductionLogic implements IProductionLogic {

    @Override
    public ArrayList<Production> getProductions(String titlePart) {
        return ProductionHandler.getInstance().getProductions(titlePart);
    }

    @Override
    public ArrayList<Production> getProductions() {
        return ProductionHandler.getInstance().getProductions();
    }

    @Override
    public Production getProduction(int id) {
        return ProductionHandler.getInstance().getProduction(id);
    }

    @Override
    public boolean createProduction(Production production) {
        return ProductionHandler.getInstance().createProduction(production);
    }

    @Override
    public boolean deleteProduction(int id) {
        return ProductionHandler.getInstance().deleteProduction(id);
    }

    @Override
    public boolean updateProductionTitle(String title, int production_id) {
        return ProductionHandler.getInstance().updateProductionTitle(title, production_id);
    }

    @Override
    public boolean updateProductionDeadline(Date deadline, int production_id) {
        return ProductionHandler.getInstance().updateProductionDeadline(deadline, production_id);
    }

    @Override
    public boolean updateProductionStatus(int status, int production_id) {
        return ProductionHandler.getInstance().updateProductionStatus(status, production_id);
    }

    @Override
    public ArrayList<Production> getProductionsLinkedToProducer(int producer_id) {
        return ProductionHandler.getInstance().getProductionsLinkedToProducer(producer_id);
    }

    @Override
    public Production getProduction(String title) {
        return ProductionHandler.getInstance().getProduction(title);
    }


}
