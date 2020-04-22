package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Production;

import java.util.List;

public interface IProductionHandler {
    public List<Production> getProductions();
    public Production getProduction(int id);
    public List<Production> getProductions(String titlePart);
    public boolean createProduction(Production production);
    public boolean deleteProduction(int id);
    public boolean updateProduction(String title, int production_id);
}
