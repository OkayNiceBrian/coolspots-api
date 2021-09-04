package coolspots;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
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
	ResponseEntity<?> newSpot(@RequestBody Spot newSpot) {
		EntityModel<Spot> entityModel = assembler.toModel(repository.save(newSpot));
		
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
				.toUri()).body(entityModel);
	}
	
	@GetMapping("/spots/{id}")
	EntityModel<Spot> one(@PathVariable Long id) {
		
		Spot spot = repository.findById(id)
				.orElseThrow(() -> new SpotNotFoundException(id));
		
		return assembler.toModel(spot);
	}
	
	@PutMapping("spots/{id}")
	ResponseEntity<?> updateSpot(@RequestBody Spot newSpot, @PathVariable Long id) {
		
		Spot updatedSpot = repository.findById(id)
				.map(spot -> {
					spot.setName(newSpot.getName());
					spot.setDescription(newSpot.getDescription());
					spot.setTags(newSpot.getTags());
					spot.setLatitude(newSpot.getLatitude());
					spot.setLongitude(newSpot.getLongitude());
					spot.setVisible(newSpot.getVisible());
					spot.setUserId(newSpot.getUserId());
					return repository.save(spot);
				})
				.orElseGet(() -> {
					newSpot.setId(id);
					return repository.save(newSpot);
				});
		
		EntityModel<Spot> entityModel = assembler.toModel(updatedSpot);
		
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
				.toUri()).body(entityModel);
	}
	
	@DeleteMapping("/spots/{id}")
	ResponseEntity<?> deleteSpot(@PathVariable Long id) {
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
