package coolspots;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Spot {

	private @Id @GeneratedValue Long id;
	private String name;
	private String description;
	private ArrayList<String> tags;
	private double latitude;
	private double longitude;
	
	public Spot() {
	}
	
	public Spot(String name, String description, ArrayList<String> tags, double lat, double lng) {
		this.name = name;
		this.description = description;
		this.tags = (ArrayList<String>) tags.clone();
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

	public ArrayList<String> getTags() {
		return tags;
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
		return "Spot{" + "id=" + this.id + ", name='" + this.name + '\'' + ", latitude='" + this.latitude + '\'' + ", longitude='" + this.longitude + '\'' + '}';
	}
	
}
