package com.mycompany.creditsystem.domain;

public interface IProductionCompanyHandler {

    public ProductionCompany getProductionCompany(int id);
    public void createProductionCompany (String name, String password);
    public void deleteProductionCompany(int id);
    public void updateProductionCompany(int id);
    
}
