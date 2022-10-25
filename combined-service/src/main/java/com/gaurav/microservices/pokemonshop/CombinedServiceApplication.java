package com.gaurav.microservices.pokemonshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CombinedServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CombinedServiceApplication.class, args);
	}

}
