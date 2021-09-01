package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Spot {

	private @Id @GeneratedValue Long id;
	private String name;
	private String description;
	
	@ElementCollection
	private List<String> tags = new ArrayList<String>();
	private double latitude;
	private double longitude;
	
	public Spot() {
	}
	
	public Spot(String name, String description, List<String> tags, double lat, double lng) {
		this.name = name;
		this.description = description;
		this.setTags(tags);
		this.latitude = lat;
		this.longitude = lng;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}
	
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public void addTag(String tag) {
		this.tags.add(tag);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Spot))
			return false;
		Spot spot = (Spot) o;
		return Objects.equals(this.id, spot.id) && Objects.equals(this.name, spot.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}
	
	@Override
	public String toString() {
		return "Spot{" + "id=" + this.id + ", name='" + this.name + '\'' + ", description='" + this.description + '\'' + ", latitude='" + this.latitude + '\'' + ", longitude='" + this.longitude + '\'' + '}';
	}
	
}
