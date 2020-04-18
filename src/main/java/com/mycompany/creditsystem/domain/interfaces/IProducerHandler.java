package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.Producer;
import com.mycompany.creditsystem.persistence.ProducerHandler;

public interface IProducerHandler {
    public Producer getProducer(int id);
    public boolean createProducer(Producer producer);
    public void deleteProducer(int id);
    public void updateProducer(int id);
}
