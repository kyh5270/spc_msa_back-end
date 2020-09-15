package eu.dreamix.spc.service;

import eu.dreamix.spc.entity.RandomDateMessage;

public interface TimecheckService {
	
	int timecheckRule(String beforeTime, String nowTime);
	
	Iterable<RandomDateMessage> getMessage();
}
