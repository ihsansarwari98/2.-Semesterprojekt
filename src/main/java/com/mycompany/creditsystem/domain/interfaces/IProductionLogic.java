package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Production;

import java.util.ArrayList;
import java.util.Date;

public interface IProductionLogic {

    public ArrayList<Production> getProductions();
    public Production getProduction(int id);
    public ArrayList<Production> getProductions(String titlePart);
    public boolean createProduction(Production production);
    public boolean deleteProduction(int id);
    public boolean updateProductionTitle(String title, int production_id);
    public boolean updateProductionDeadline(Date deadline, int production_id);
    public boolean updateProductionStatus(int status, int production_id);
    public ArrayList<Production> getProductionsLinkedToProducer(int producer_id);

}
