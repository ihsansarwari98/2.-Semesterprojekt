package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.ProductionCompany;

public interface IProductionCompanyHandler {

    public ProductionCompany getProductionCompany(int id);
    public boolean createProductionCompany (ProductionCompany productionCompany);
    public boolean deleteProductionCompany(int id);
    public void updateProductionCompany(int id);

}
