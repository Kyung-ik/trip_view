package com.tripviewBoard.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TripviewBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripviewBoardApplication.class, args);
	}

}
