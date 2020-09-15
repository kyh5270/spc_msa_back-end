package eu.dreamix.spc.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.dreamix.spc.entity.RandomDateMessage;
import eu.dreamix.spc.entity.SimulatorInput;
import eu.dreamix.spc.kafka.producer.Sender;
import eu.dreamix.spc.kafka.producer.TwitterProducer;

@Component
public class SimulatorServiceImpl implements SimulatorService {

	Logger logger = LoggerFactory.getLogger(SimulatorServiceImpl.class.getName());
	
	private Sender sender;
	
	@Autowired
	public SimulatorServiceImpl(Sender sender) {
		this.sender = sender;
	}
	
    @Override
    public void registerKeyword_self(RandomDateMessage input) {
    	
    	logger.info("registerKeyword_self - Start");
    	
    	String Topic = "SIMULATOR_CREATED_TOPIC"; 
    	int sleepTime = 2000;
    	
    	// setCreated_at & setText
    	for(int i = 0; i < 100; i++) {
    		
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    		Date time = new Date();
    		String stringTime = format.format(time);
    		
    		input.setCreatedTime(stringTime);    		
    		input.setText(input.getKeyword() + i);    		
    		input.setValue((int)(Math.random() * (1000 - 0 + 1) + 0));
    		
        	sender.send(Topic, input);
        	
        	//random number generate
        	//sleepTime = (int)(Math.random() * (1000 - 0 + 1) + 0);
        	
    		try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	    
    }
	
    @Override
    public void registerKeyword(SimulatorInput input) {
    	
    	logger.info("registerKeyword - Start");
    	
    	String inputParam = input.getKeyword();    	
    	logger.info("registerKeyword - inputParam : " + inputParam);
    	    	
    	TwitterProducer.generator(inputParam);    	 	     
    }

	@Override
	public void kafkaStop() {
		
		logger.info("kafkaStop - Start");			
		TwitterProducer.generator_stop();
	}
}
