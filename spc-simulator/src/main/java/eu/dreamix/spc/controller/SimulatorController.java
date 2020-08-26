package eu.dreamix.spc.controller;

import eu.dreamix.spc.entity.Simulator;
import eu.dreamix.spc.kafka.producer.TestProducer;
import eu.dreamix.spc.service.SimulatorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.ribbon.proxy.annotation.Http.Header;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimulatorController {

    Logger logger = LoggerFactory.getLogger(SimulatorController.class.getName());
	
    private SimulatorService simulatorService;

    @Autowired
    SimulatorController(SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }

//    @RequestMapping(method = RequestMethod.GET, path = "/members")
//    public ResponseEntity<Iterable<Simulator>> getAll() {
//
//        Iterable<Simulator> all = simulatorService.findAll();
//
//        return new ResponseEntity<>(all, HttpStatus.OK);
//    }

	@RequestMapping(method = RequestMethod.POST, path = "/generate")
    public ResponseEntity<Simulator> generate(@RequestBody Simulator input) {

    	logger.info("generate - Start");
    	
    	// Simulator Start
    	String result = simulatorService.registerKeyword(input);
  	
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.POST, path = "/stop")
    public ResponseEntity<Simulator> stop(@RequestBody Simulator input) {

    	logger.info("stop - Start");
    	
    	// Simulator Stop
    	simulatorService.kafkaStop();
  	
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
