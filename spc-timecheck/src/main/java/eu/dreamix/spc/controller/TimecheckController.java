package eu.dreamix.spc.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.dreamix.spc.entity.RandomDateMessage;
import eu.dreamix.spc.kafka.consumer.Receiver;
import eu.dreamix.spc.service.TimecheckService;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class TimecheckController {

    Logger logger = LoggerFactory.getLogger(TimecheckController.class.getName());
	
    private TimecheckService timecheckService;
    
    @Autowired
    TimecheckController(TimecheckService timecheckService) {
        this.timecheckService = timecheckService;
    }
    
    @GetMapping("/randomMessage")
    public ResponseEntity<Iterable<RandomDateMessage>> random_message(){
    	
        Iterable<RandomDateMessage> all = timecheckService.getMessage();
        
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    
    @GetMapping("/hello")
    public String hello(){
    	return "안녕하세요. 현재 서버시간은 "+new Date() +"입니다. \n";
    	
    	//return new Receiver();
    }

//    @MessageMapping("/message")//@MessageMapping works for WebSocket protocol communication. This defines the URL mapping.
//    //@SendTo("/topic/public")//websocket subscribe topic& direct send
//    public void sendMessage(ChattingMessage message) throws Exception {
//        message.setTimeStamp(System.currentTimeMillis());
//        //chattingHistoryDAO.save(message);
//        sender.send(BOOT_TOPIC, message);
//
//    }
}
