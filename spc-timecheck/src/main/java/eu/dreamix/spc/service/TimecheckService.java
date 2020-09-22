package eu.dreamix.spc.service;

import eu.dreamix.spc.entity.RandomDateMessage;

public interface TimecheckService {
	
	int timecheckRule(String beforeTime, String nowTime);
	
	int cal_Rs(int beforeValue, int nowValue);
	
	int cal_XCL();
	
	int cal_XUCL();
	
	int cal_XLCL();
	
	int cal_RsCL();
	
	int cal_RsUCL();
	
	Iterable<RandomDateMessage> getMessage();
}
