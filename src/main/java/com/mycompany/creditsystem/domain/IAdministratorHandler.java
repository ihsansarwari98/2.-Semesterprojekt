package com.mycompany.creditsystem.domain;

public interface IAdministratorHandler {

    public Producer getProducer(int id);
    public void createProducer(String name, String username);
    public void deleteProducer(int id);
    public Producer updateProducer(int id);
    public ProductionCompany getProductionCompany(int id);
    public void createProductionCompany(String name, String username);
    public void deleteProductionCompany(int id);
    public void updateProductionCompany(int id);

}
