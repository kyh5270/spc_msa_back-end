package eu.dreamix.spc.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import eu.dreamix.spc.entity.RandomDateMessage;

public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, RandomDateMessage> kafkaTemplate;

    public void send(String topic, RandomDateMessage payload) {
    	LOGGER.info("send - Start");
    	
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic, payload);
    }
    
}
