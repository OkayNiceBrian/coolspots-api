package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotController {

	private final SpotRepository repository;
	
	public SpotController(SpotRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/spots")
	List<Spot> all() {
		return repository.findAll();
	}
	
	@PostMapping("/spots")
	Spot newSpot(@RequestBody Spot newSpot) {
		return repository.save(newSpot);
	}
	
	@GetMapping("/spots/{id}")
	Spot one(@PathVariable Long id) {
		
		return repository.findById(id)
				.orElseThrow(() -> new SpotNotFoundException(id));
	}
	
	@PutMapping("spots/{id}")
	Spot updateSpot(@RequestBody Spot newSpot, @PathVariable Long id) {
		
		return repository.findById(id)
				.map(spot -> {
					spot.setName(newSpot.getName());
					spot.setDescription(newSpot.getDescription());
					spot.setTags(newSpot.getTags());
					spot.setLatitude(newSpot.getLatitude());
					spot.setLongitude(newSpot.getLongitude());
					return repository.save(spot);
				})
				.orElseGet(() -> {
					newSpot.setId(id);
					return repository.save(newSpot);
				});
	}
	
	@DeleteMapping("/spots/{id}")
	void deleteSpot(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
