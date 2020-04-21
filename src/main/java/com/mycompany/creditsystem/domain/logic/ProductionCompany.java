package com.mycompany.creditsystem.domain.logic;

import java.util.ArrayList;

public class ProductionCompany extends User {


    private ArrayList<Production> companyProductions;
    private ArrayList<Producer> companyProducers;

    public ProductionCompany(String name, String username, String password) {
        super(name, username, password);
        companyProductions = new ArrayList<>();
        companyProducers = new ArrayList<>();
        super.setAccessRole(AccessRole.productionCompany);
    }

    public ArrayList<Production> getCompanyProductions() {
        return companyProductions;
    }

    public void addProduction(Production production) {
        companyProductions.add(production);
    }

    public void removeCompanyProduction(Production production) {
        companyProductions.remove(production);
    }

    public ArrayList<Producer> getCompanyProducers() {
        return companyProducers;
    }


    public void removeCompanyProducer(Producer producer) {
        companyProducers.remove(producer);
    }

    public void createCompanyProducer(String name, String username, String password) {
        companyProducers.add(new Producer(name, username, password));
    }



    }
