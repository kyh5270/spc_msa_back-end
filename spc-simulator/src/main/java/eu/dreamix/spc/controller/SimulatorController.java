package eu.dreamix.spc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.dreamix.spc.entity.RandomDateMessage;
import eu.dreamix.spc.entity.SimulatorInput;
import eu.dreamix.spc.service.SimulatorService;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimulatorController {

    Logger logger = LoggerFactory.getLogger(SimulatorController.class.getName());
	
    private SimulatorService simulatorService;
    
    @Autowired
    SimulatorController(SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }

	@RequestMapping(method = RequestMethod.POST, path = "/generate_self")
    public void generate_self(@RequestBody RandomDateMessage input) {

    	logger.info("generate_self - Start");
    	simulatorService.registerKeyword_self(input);
    }
    
	@RequestMapping(method = RequestMethod.POST, path = "/generate")
    public ResponseEntity<SimulatorInput> generate(@RequestBody SimulatorInput input) {

    	logger.info("generate - Start");
    	simulatorService.registerKeyword(input);
    	
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("AUTHCODE","xxxxxxx");
        header.add("TOKEN", "xxxxxx");
    	
        return new ResponseEntity<>(header, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.POST, path = "/stop")
    public ResponseEntity<SimulatorInput> stop(@RequestBody SimulatorInput input) {

    	logger.info("stop - Start");
    	simulatorService.kafkaStop();
  	
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("AUTHCODE","xxxxxxx");
        header.add("TOKEN", "xxxxxx");
    	
        return new ResponseEntity<>(header, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.POST, path = "/test")
    public ResponseEntity<SimulatorInput> test(@RequestBody SimulatorInput input) {

    	logger.info("test - Start");
  	
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("AUTHCODE","xxxxxxx");
        header.add("TOKEN", "xxxxxx");
    	
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
