package eu.dreamix.spc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpcSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpcSimulatorApplication.class, args);
	}
}
