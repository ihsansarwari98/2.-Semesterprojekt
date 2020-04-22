package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.ProductionCompany;

public interface IProductionCompanyHandler {

    public ProductionCompany getProductionCompany(int id);
    public boolean createProductionCompany (ProductionCompany productionCompany);
    public boolean deleteProductionCompany(int id);
    public boolean updateProductionCompany(String name, int production_company_id);
    public boolean linkProducerToProductionCompany(int producer_id, int production_company_id);
    public boolean removeProducerFromProductionCompany(int producer_id, int production_company_id);
}
