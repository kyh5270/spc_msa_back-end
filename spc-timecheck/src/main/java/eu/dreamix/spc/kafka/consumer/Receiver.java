package eu.dreamix.spc.kafka.consumer;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.dreamix.spc.entity.RandomDateMessage;
import eu.dreamix.spc.entity.TwitterMessage;
import eu.dreamix.spc.service.TimecheckService;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private TimecheckService timecheckService;

    @Autowired
    SimpMessagingTemplate template;
    
    private String beforeCreated_at = "0";
    private String nowCreated_at;
    private int timeDiff;
    
    private int controlchartWindow = 10;
    
    private ArrayList<Integer> listValue = new ArrayList<>(controlchartWindow);
    private ArrayList<Long> listRsValue = new ArrayList<>(controlchartWindow-1);
    private int sumValue;
    private int beforeValue = -999;
    private int nowValue;
    private long rsValue;
    private long xControlCl;
    private double xControlUcl;
    private double xControlLcl;
    private long rsControlCl;
    private double rsControlUcl;
    
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
        
        nowValue = payload.getValue();        
        listValue.add(payload.getValue());
        if(beforeValue > -999) {            
            rsValue = timecheckService.cal_Rs(beforeValue, nowValue);
            listRsValue.add(rsValue);
        }        
        beforeValue = nowValue;

        LOGGER.info("listValue.size()   = '{}'", listValue.size());  
        LOGGER.info("listValue.get(1)   = '{}'", listValue.get(1));  
        LOGGER.info("listRsValue.size() = '{}'", listRsValue.size());  
        LOGGER.info("listRsValue.get(1) = '{}'", listRsValue.get(1));  
        
        if(listRsValue.size() >= (controlchartWindow - 1)) {
        	sumValue = 0;
        	for(Integer i : listValue) {
        		sumValue += i;
        	}
        	
        	rsControlCl = sumValue / (controlchartWindow-1);

            listRsValue.remove(0);
        }
        
        if(listValue.size() >= controlchartWindow) {
        	sumValue = 0;
        	for(Integer i : listValue) {
        		sumValue += i;
        	}
        	
        	xControlCl = sumValue / controlchartWindow;

            listValue.remove(0);
        }
        
        xControlUcl = xControlCl + 2.66 * rsControlCl;
        xControlLcl = xControlCl - 2.66 * rsControlCl;
        rsControlUcl = 3.27 * rsControlCl;
        
        LOGGER.info("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※");
        LOGGER.info("※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※");
        
        LOGGER.info("Value       = '{}'", Integer.toString(payload.getValue()));  
        LOGGER.info("RsValue       = '{}'", rsValue);  
        LOGGER.info("XControlCL       = '{}'", xControlCl);  
        LOGGER.info("XControlUCL       = '{}'", xControlUcl);  
        LOGGER.info("XControlLCL       = '{}'", xControlLcl);  
        LOGGER.info("RsControlCL       = '{}'", rsControlCl);  
        LOGGER.info("RsControlUCL       = '{}'", rsControlUcl);  
        
        HashMap<String, String> msg = new HashMap<>();
        msg.put("Id", Long.toString(payload.getId()));
        msg.put("Keyword", payload.getKeyword());
        msg.put("CreatedTime", payload.getCreatedTime());
        msg.put("Text", payload.getText());
        //x값
        msg.put("Value", Integer.toString(payload.getValue()));  
        //Rs값
        msg.put("RsValue", Long.toString(rsValue));  
        //X관리도 CL값
        msg.put("XControlCL", Long.toString(xControlCl));  
        //X관리도 UCL값
        msg.put("XControlUCL", Double.toString(xControlUcl));  
        //X관리도 LCL값
        msg.put("XControlLCL", Double.toString(xControlLcl));  
        //Rs관리도 CL값
        msg.put("RsControlCL", Long.toString(rsControlCl));  
        //Rs관리도 UCL값
        msg.put("RsControlUCL", Double.toString(rsControlUcl));  
                
        ObjectMapper mapper = new ObjectMapper();
        try {
			String json = mapper.writeValueAsString(msg);
			
	        LOGGER.info("json       = '{}'", json);    
			
	        template.convertAndSend("/topic/test", json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
                
        //timecheckService.sendSimpleMessage(payload);
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
