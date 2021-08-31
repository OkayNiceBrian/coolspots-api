package com.example.demo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SpotModelAssembler implements RepresentationModelAssembler<Spot, EntityModel<Spot>> {
	
	@Override
	public EntityModel<Spot> toModel(Spot spot) {
		return EntityModel.of(spot, 
				linkTo(methodOn(SpotController.class).one(spot.getId())).withSelfRel(),
				linkTo(methodOn(SpotController.class).all()).withRel("spots")
				);
	}
}