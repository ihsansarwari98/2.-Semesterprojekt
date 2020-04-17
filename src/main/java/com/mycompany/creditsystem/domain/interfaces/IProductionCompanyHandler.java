package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.ProductionCompany;

public interface IProductionCompanyHandler {

    public ProductionCompany getProductionCompany(int id);
    public void createProductionCompany (String name, String password);
    public void deleteProductionCompany(int id);
    public void updateProductionCompany(int id);
    
}
