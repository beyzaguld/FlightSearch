package com.amadeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FlightsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsearchApplication.class, args);
	}

}
