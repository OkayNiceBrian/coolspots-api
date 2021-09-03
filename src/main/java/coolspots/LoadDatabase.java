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
	CommandLineRunner initDatabase(UserRepository userRepo, SpotRepository spotRepo) {
		
		return args -> {
//			log.info("Preloading " + userRepo.save(new User("Brian", "brian@email.com", "password123")));
//			log.info("Preloading " + userRepo.save(new User("Vince", "vince@email.com", "heythere321")));
//			
//			log.info("Preloading " + spotRepo.save(new Spot("The Bush Spot", "Sick manny pad spot.", new ArrayList<String>(), "Tumwater, WA", 38.8951, -77.0364, true)));
//			log.info("Preloading " + spotRepo.save(new Spot("Garfield", "Nice school area to skate.", new ArrayList<String>(), "Olympia, WA", 47.6062, 122.3321, true)));
		};
	}
}
