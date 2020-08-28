package eu.dreamix.spc.service;

import eu.dreamix.spc.entity.RandomDateMessage;
import eu.dreamix.spc.entity.SimulatorInput;

public interface SimulatorService {

	void registerKeyword_self(RandomDateMessage input);
	
	void registerKeyword(SimulatorInput input);
    
    void kafkaStop();
}
