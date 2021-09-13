package coolspots;

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
	CommandLineRunner initDatabase(SpotRepository spotRepo) {
		
		return args -> {
//			log.info("Preloading " + spotRepo.save(new Spot("The Bush Spot", "Sick manny pad spot.", new ArrayList<String>(), "Tumwater, WA", 47.0073, -122.9093, true, new ArrayList<String>())));
//			log.info("Preloading " + spotRepo.save(new Spot("Garfield", "Nice school area to skate.", new ArrayList<String>(), "Olympia, WA", 47.6062, -122.3331, true, new ArrayList<String>())));
//			log.info("Preloading " + spotRepo.save(new Spot("F Lot", "Chillin parking lot.", new ArrayList<String>(), "Olympia, WA", 47.6062, -122.3329, true, new ArrayList<String>())));
//			log.info("Preloading " + spotRepo.save(new Spot("Eiffel Tower", "Some thingy.", new ArrayList<String>(), "Paris, France", 48.8566, 2.3522, true, new ArrayList<String>())));
//			log.info("Preloading " + spotRepo.save(new Spot("Saigon Rendesvouz", "Best damn restaurant.", new ArrayList<String>(), "Olympia, WA", 47.6062, -122.3325, true, new ArrayList<String>())));
		};
	}
}
