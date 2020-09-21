package eu.dreamix.spc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableEurekaClient
@SpringBootApplication
@EnableAsync
public class SpcTimecheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpcTimecheckApplication.class, args);
	}
}
