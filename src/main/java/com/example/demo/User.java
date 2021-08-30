package com.example.demo;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User {

	private @Id @GeneratedValue Long id;
	private String name;
	private String email;
	private String password;
	private ArrayList<Spot> spots;

	public User() {
	}

	public User(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.setPassword(password);
		this.spots = new ArrayList<Spot>();
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public ArrayList<Spot> getSpots() {
		return this.spots;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(password);
	}
	
	public void setSpots(ArrayList<Spot> spots) {
		this.spots = (ArrayList<Spot>) spots.clone();
	}

	public void addSpot(Spot spot) {
		this.spots.add(spot);
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(this.id, user.id) && Objects.equals(this.email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.email);
	}

	@Override
	public String toString() {
		return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + '}';
	}
}
