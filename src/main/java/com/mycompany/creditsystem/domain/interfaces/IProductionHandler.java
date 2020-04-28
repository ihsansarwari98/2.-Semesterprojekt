package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.persistence.Production;

import java.util.ArrayList;
import java.util.Date;

public interface IProductionHandler {
    public ArrayList<Production> getProductions();
    public Production getProduction(int id);
    public ArrayList<Production> getProductions(String titlePart);
    public boolean createProduction(Production production);
    public boolean deleteProduction(int id);
    public boolean updateProductionTitle(String title, int production_id);
    public boolean updateProductionDeadline(Date deadline, int production_id);
    public boolean updateProductionStatus(int status, int production_id);
    public ArrayList<Production> getProductionsLinkedToProducer(int producer_id);
    public Production getProduction(String title);
    public boolean linkProductionToProducer(int production_id, int producer_id);
    public ArrayList<Production> getProductionsLinkedToProductionCompany(int user_id);
    public boolean linkProductionToProductionCompany(int production_id, int production_company_id);
    public boolean addCreditAndRoleToProduction(int production_id, int credit_id, int role_id);
}
