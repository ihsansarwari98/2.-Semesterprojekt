package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Production;
import com.mycompany.creditsystem.persistence.ProductionHandler;

import java.util.Date;
import java.util.List;

public interface IProductionLogic {

    public List<Production> getProductions();
    public Production getProduction(int id);
    public List<Production> getProductions(String titlePart);
    public boolean createProduction(Production production);
    public boolean deleteProduction(int id);
    public boolean updateProductionTitle(String title, int production_id);
    public boolean updateProductionDeadline(Date deadline, int production_id);
    public boolean updateProductionStatus(int status, int production_id);

}
