package com.spc.spcdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpcDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpcDiscoveryApplication.class, args);
	}

}
