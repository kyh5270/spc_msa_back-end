package eu.dreamix.spc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import eu.dreamix.spc.entity.RandomDateMessage;

import eu.dreamix.spc.kafka.consumer.Receiver;

@Component
public class TimecheckServiceImpl implements TimecheckService {

	Logger logger = LoggerFactory.getLogger(TimecheckServiceImpl.class.getName());

	@Override
	public int timecheckRule(String beforeTime, String nowTime) {
		
		Date beforeDate;
		Date nowDate;
		long calDate;
		long calDateDiff = 0;
		
    	logger.info("timecheckRule - Start");
		
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    	
		try {
			beforeDate = format.parse(beforeTime);
		    nowDate = format.parse(nowTime);
		    
	    	calDate = beforeDate.getTime() - nowDate.getTime();
	    	
	    	calDateDiff = Math.abs(calDate);
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return (int) calDateDiff;
	}

	@Override
	public Iterable<RandomDateMessage> getMessage() {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cal_Rs(int beforeValue, int nowValue) {
		// TODO Auto-generated method stub		
		
		return Math.abs(beforeValue - nowValue);
	}

	@Override
	public int cal_XCL() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cal_XUCL() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cal_XLCL() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cal_RsCL() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cal_RsUCL() {
		// TODO Auto-generated method stub
		return 0;
	}

}
