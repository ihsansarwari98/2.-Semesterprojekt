package com.mycompany.creditsystem.domain.logic;

import java.util.ArrayList;

public class Administrator extends User {

    private ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();

    public Administrator(String name, String username, String password) {
        super(name, username, password);
        super.setAccessRole(AccessRole.admin);
    }

    public void createProductionCompany(String ProductionCompanyName, String ProductionCompanyUsername,String password) {
        ProductionCompany productionCompany = new ProductionCompany(ProductionCompanyName, ProductionCompanyUsername,
            password);
        productionCompanies.add(productionCompany);
    }

    public void removeProductionCompany(ProductionCompany productionCompany) {
        productionCompanies.remove(productionCompany);
    }


}
