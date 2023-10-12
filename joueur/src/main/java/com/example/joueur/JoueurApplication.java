package com.example.joueur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JoueurApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoueurApplication.class, args);
	}

}
