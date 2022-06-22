package com.tripview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TripviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripviewApplication.class, args);
	}

}
