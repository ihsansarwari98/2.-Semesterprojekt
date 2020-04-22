package com.mycompany.creditsystem.domain.logicinterface;

import com.mycompany.creditsystem.domain.logic.Producer;

public interface IProducerLogic {
    public Producer getProducer(int id);
    public boolean createProducer(Producer producer);
    public void deleteProducer(int id);
    public void updateProducer(int id);
}
