package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IAdministratorHandler;
import com.mycompany.creditsystem.domain.logic.Producer;
import com.mycompany.creditsystem.domain.logic.ProductionCompany;

public class AdministratorHandler implements IAdministratorHandler {

    @Override
    public Producer getProducer(int id) {
        return null;
    }

    @Override
    public void createProducer(String name, String username) {

    }

    @Override
    public void deleteProducer(int id) {

    }

    @Override
    public Producer updateProducer(int id) {
        return null;
    }

    @Override
    public ProductionCompany getProductionCompany(int id) {
        return null;
    }

    @Override
    public void createProductionCompany(String name, String username) {

    }

    @Override
    public void deleteProductionCompany(int id) {

    }

    @Override
    public void updateProductionCompany(int id) {

    }
}