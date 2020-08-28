package eu.dreamix.spc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

}
