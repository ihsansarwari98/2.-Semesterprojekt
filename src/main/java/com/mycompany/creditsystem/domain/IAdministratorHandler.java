package com.mycompany.creditsystem.domain;

public interface IAdministratorHandler {

    public Producer getProducer(int producerId);
    public void createProducer(String name, String username);
    public void deleteProducer(int producerId);
    public Producer updateProducer(int producerId);
    public ProductionCompany getProductionCompany(int productionCompanyId);
}
