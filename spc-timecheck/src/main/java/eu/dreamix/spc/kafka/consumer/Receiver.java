package eu.dreamix.spc.kafka.consumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import eu.dreamix.spc.entity.RandomDateMessage;
import eu.dreamix.spc.entity.TwitterMessage;
import eu.dreamix.spc.service.TimecheckService;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private TimecheckService timecheckService;

    private String test;
    private String beforeCreated_at = "0";
    private String nowCreated_at;
    private int timeDiff;
    
    public void listenToPartition(@Payload String message,	@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
    	LOGGER.info("Received Message: " + message + "from partition: " + partition);		
	}
    
//    @KafkaListener(topics = "${spring.kafka.topic.twitter_tweets}")
//    @KafkaListener(topics = "twitter_tweets")
    @KafkaListener(topics = "SIMULATOR_CREATED_TOPIC")
    public void receive(RandomDateMessage payload) { 	
    	
    	//LOGGER.info("Received Message: " + message + "from partition: " + partition);		
        LOGGER.info("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※");
        LOGGER.info("received Id          = '{}'", payload.getId());
        LOGGER.info("received Keyword     = '{}'", payload.getKeyword());
        LOGGER.info("received CreatedTime = '{}'", payload.getCreatedTime());
        LOGGER.info("received Description = '{}'", payload.getText());        
        LOGGER.info("received Value       = '{}'", payload.getValue());    
        
        nowCreated_at = payload.getCreatedTime();
        
        if(!beforeCreated_at.equals("0")) {
            timeDiff = timecheckService.timecheckRule(beforeCreated_at, nowCreated_at);
            LOGGER.info("timeDiff = '0.{}' second different", timeDiff); 
        }
        
        beforeCreated_at = payload.getCreatedTime();
        
        // 특정 경로에 js 파일 저장
        JSONObject obj = new JSONObject();
    	obj.put("region", payload.getCreatedTime());
    	obj.put("location", payload.getId());
    	obj.put("details", payload.getText());
//    	obj.put("age", new Integer(100));
     
//    	JSONArray list = new JSONArray();
//    	list.add("msg 1");
//    	list.add("msg 2");
//    	list.add("msg 3");
     
//    	obj.put("messages", list);
     
    	try {
    		FileWriter file = new FileWriter("c:\\test.js");
    		file.write("showTours({\"tours\": [");
    		file.flush();
    		file.close();
    		
    		File f1 = new File("c:\\test.js");
    		FileWriter fw1 = new FileWriter(f1, true);  		
    		fw1.write(obj.toJSONString());
    		fw1.flush();
    		fw1.close();
     
    		File f2 = new File("c:\\test.js");
    		FileWriter fw2 = new FileWriter(f2, true);
    		fw2.write("]});");
    		fw2.flush();
    		fw2.close();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
        
//        timecheckService.sendSimpleMessage(payload);
//        latch.countDown();
    }
    
    @KafkaListener(topics = "twitter_tweets")
    public void receive(TwitterMessage payload) { 	
    	
    	//LOGGER.info("Received Message: " + message + "from partition: " + partition);		
        LOGGER.info("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※");
        LOGGER.info("received Keyword     = '{}'", payload.getKeyword());
        
        LOGGER.info("received Id          = '{}'", payload.getId());
        LOGGER.info("received Id_str      = '{}'", payload.getId_str());
        LOGGER.info("received CreatedTime = '{}'", payload.getCreated_at());
        LOGGER.info("received Description = '{}'", payload.getText());     
        LOGGER.info("received Source      = '{}'", payload.getSource());   
        LOGGER.info("received Truncated   = '{}'", payload.getTruncated());   
        LOGGER.info("received lang        = '{}'", payload.getLang());    
        
    }
    
    
}
