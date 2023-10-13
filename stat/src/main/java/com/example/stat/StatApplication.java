package com.example.stat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StatApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatApplication.class, args);
	}

}
