package eu.dreamix.spc.kafka.consumer;

import eu.dreamix.spc.entity.dto.SpcDto;
import eu.dreamix.spc.service.TimecheckService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private TimecheckService timecheckService;

    @KafkaListener(topics = "twitter_tweets")
    public void receive(SpcDto payload) {
    	
        LOGGER.info("received payload.getCreated_at='{}'", payload.getCreated_at());
        LOGGER.info("received payload.getId='{}'", payload.getId());
        LOGGER.info("received payload.getText='{}'", payload.getText());        
        
        // 특정 경로에 js 파일 저장
        JSONObject obj = new JSONObject();
    	obj.put("region", payload.getCreated_at());
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
}
