package com.mycompany.creditsystem.persistence;

import com.mycompany.creditsystem.domain.interfaces.IProducerHandler;
import com.mycompany.creditsystem.domain.logic.Producer;

public class ProductionCompanyHandler implements IProducerHandler {
    @Override
    public Producer getProducer(int id) {
        return null;
    }

    @Override
    public boolean createProducer(Producer producer) {
        return false;
    }

    @Override
    public void deleteProducer(int id) {

    }

    @Override
    public void updateProducer(int id) {

    }
}
