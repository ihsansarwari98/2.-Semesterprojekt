package com.mycompany.creditsystem.domain.logic;

public class Administrator extends User {
    public Administrator(String name, String username, String password) {
        super(name, username, password);
    }

    public void createProductionCompany(String ProductionCompanyName, String ProductionCompanyUsername,String password) {
    ProductionCompany productionCompany = new ProductionCompany(ProductionCompanyName, ProductionCompanyUsername,
            password);
    }

    public void removeProductionCompany(ProductionCompany productionCompany) {

    }


}
