package com.ftninformatika.jwd.modul3.flowrSpot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ftninformatika.jwd.modul3.flowrSpot.enumeration.UserRole;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Enumerated(EnumType.STRING)
    private UserRole userRole;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favorite> favorites = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Sighting> sightings = new ArrayList<>();

	public User() {
		super();
	}

	public User(Long id, String firstName, String lastName, 
			List<Favorite> favorites, List<Like> likes, List<Sighting> sightings,
			String password, String username, UserRole userRole) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.favorites = favorites;
		this.likes = likes;
		this.sightings = sightings;
		this.password = password;
		this.username = username;
		this.userRole = userRole;
	}

	public List<Sighting> getSightings() {
		return sightings;
	}

	public void setSightings(List<Sighting> sightings) {
		this.sightings = sightings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
}
