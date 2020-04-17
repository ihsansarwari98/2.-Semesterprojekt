package com.mycompany.creditsystem.domain;

import java.util.ArrayList;

public class ProductionCompany extends User {



    ArrayList<Production> companyProductions;
    ArrayList<Producer> companyProducers;

    public ProductionCompany(String name, String username, String password) {
        super(name, username, password);
        companyProductions = new ArrayList<>();
        companyProducers = new ArrayList<>();
    }

    public ArrayList<Production> getCompanyProductions() {
        return companyProductions;
    }

    public void addProduction(Production production) {

    }

    public void removeCompanyProduction(Production production) {

    }

    public ArrayList<Producer> getCompanyProducers() {
        return companyProducers;
    }

    public void addProducerToProduction(Producer producer) {

    }

    public void removeCompanyProducer(Producer producer) {

    }

    public void createCompanyProducer(Producer producer) {

    }

    public boolean producerCanEdit(Producer producer, boolean canEdit) {
        return false;
    }
}
