package eu.dreamix.spc.service;


import eu.dreamix.spc.entity.Timecheck;
import eu.dreamix.spc.entity.dto.SpcDto;
import eu.dreamix.spc.repository.TimecheckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class TimecheckServiceImpl implements TimecheckService {

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public TimecheckRepository mailRepository;

    @Bean 
    public JavaMailSenderImpl mailSender() { 
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl(); 
        javaMailSender.setProtocol("smtp"); 
        javaMailSender.setHost("127.0.0.1"); 
        javaMailSender.setPort(25); 
        return javaMailSender; 
    }
    
    @Override
    public void sendSimpleMessage(SpcDto input) {
        try {

//            Mail newMail = new Mail();
//            newMail.setTo(input.getUsername());
//            newMail.setSubject("TestSubject");
//            newMail.setText("TestText");

//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo(newMail.getTo());
//            message.setSubject(newMail.getSubject());
//            message.setText(newMail.getText());

//            mailRepository.save(newMail);
//            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }

    }
}
