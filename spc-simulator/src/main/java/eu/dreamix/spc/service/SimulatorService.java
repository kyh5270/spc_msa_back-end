package eu.dreamix.spc.service;

import eu.dreamix.spc.entity.Simulator;

public interface SimulatorService {

	String registerKeyword(Simulator input);
    
    void kafkaStop();
}
