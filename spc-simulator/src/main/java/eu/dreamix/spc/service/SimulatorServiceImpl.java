package eu.dreamix.spc.service;

import eu.dreamix.spc.entity.Simulator;
import eu.dreamix.spc.kafka.producer.Sender;
import eu.dreamix.spc.kafka.producer.TestProducer;
import eu.dreamix.spc.repository.SimulatorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SimulatorServiceImpl implements SimulatorService {

	Logger logger = LoggerFactory.getLogger(SimulatorServiceImpl.class.getName());
	
    @Override
    public String registerKeyword(Simulator input) {
    	
    	logger.info("registerKeyword - Start");
    	
    	String inputParam = input.getKeyword();    	
    	logger.info("registerKeyword - inputParam : " + inputParam);
    	    	
    	String i = TestProducer.generator(inputParam);    	
    	System.out.println("registerKeyword - result : " + i);
    	
        return i;        
    }

	@Override
	public void kafkaStop() {
		
		logger.info("kafkaStop - Start");	
		
		TestProducer.generator_stop();
	}
}
