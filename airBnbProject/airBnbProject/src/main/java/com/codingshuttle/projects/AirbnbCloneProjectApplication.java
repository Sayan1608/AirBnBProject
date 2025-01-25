package com.codingshuttle.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AirbnbCloneProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbCloneProjectApplication.class, args);
	}

}
