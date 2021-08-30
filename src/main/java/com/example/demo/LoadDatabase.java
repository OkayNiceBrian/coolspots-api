package com.example.demo;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(UserRepository userRepo, SpotRepository spotRepo) {
		
		return args -> {
			log.info("Preloading " + userRepo.save(new User("Brian", "brianjackson1224@gmail.com", "password123")));
			log.info("Preloading " + userRepo.save(new User("Vince", "vincemej.123@gmail.com", "heythere321")));
			
			log.info("Preloading " + spotRepo.save(new Spot("The Bush Spot", "Sick manny pad spot.", new ArrayList<String>(), 38.8951, -77.0364)));
			log.info("Preloading " + spotRepo.save(new Spot("Garfield", "Nice school area to skate.", new ArrayList<String>(), 47.6062, 122.3321)));
		};
	}
}
