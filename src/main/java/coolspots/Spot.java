package coolspots;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spots")
public class Spot {

	@Id @GeneratedValue 
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@ElementCollection
	@Column(name = "tags", nullable = true)
	private List<String> tags = new ArrayList<String>();
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "latitude", nullable = false)
	private double latitude;
	
	@Column(name = "longitude", nullable = false)
	private double longitude;
	
	@Column(name = "visible", nullable = false)
	private boolean visible;
	
	public Spot() {
	}
	
	public Spot(String name, String description, List<String> tags, String city, double lat, double lng, boolean visible) {
		this.name = name;
		this.description = description;
		this.setTags(tags);
		this.city = city;
		this.latitude = lat;
		this.longitude = lng;
		this.visible = visible;
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
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
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
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getVisible() {
		return this.visible;
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
