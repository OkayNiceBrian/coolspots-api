package com.example.demo;

import java.util.ArrayList;
import java.util.List;
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
	private List<Integer> spotIds;

	public User() {
	}

	public User(String name, String email, String password) {

		this.name = name;
		this.email = email;
		this.setPassword(password);
		this.spotIds = new ArrayList<Integer>();
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

	public List<Integer> getSpots() {
		return this.spotIds;
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
	
	public void setSpots(List<Integer> spotIds) {
		for (Integer i : spotIds) {
			this.spotIds.add(i);
		}
	}

	public void addSpot(int spotId) {
		this.spotIds.add(spotId);
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
