package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
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
	private final SpotModelAssembler assembler;
	
	public SpotController(SpotRepository repository, SpotModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/spots")
	CollectionModel<EntityModel<Spot>> all() {
		List<EntityModel<Spot>> spots = repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(spots, linkTo(methodOn(SpotController.class).all()).withSelfRel());
	}
	
	@PostMapping("/spots")
	Spot newSpot(@RequestBody Spot newSpot) {
		return repository.save(newSpot);
	}
	
	@GetMapping("/spots/{id}")
	EntityModel<Spot> one(@PathVariable Long id) {
		
		Spot spot = repository.findById(id)
				.orElseThrow(() -> new SpotNotFoundException(id));
		
		return assembler.toModel(spot);
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
