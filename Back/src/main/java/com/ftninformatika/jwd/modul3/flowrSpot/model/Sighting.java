package com.ftninformatika.jwd.modul3.flowrSpot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sightings")
public class Sighting {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Flower flower;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Number latitude;
	
	@Column
	private Number longitude;

	@OneToMany(mappedBy = "sighting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();
	
	@OneToMany(mappedBy = "sighting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "sightings")
	private List<User> users = new ArrayList<>();
	
	public Sighting() {
		super();
	}

	public Sighting(Long id, Flower flower, String name, String description, Number latitude, Number longitude,
			List<Like> likes, List<User> users, List<Comment> comments) {
		super();
		this.id = id;
		this.flower = flower;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.likes = likes;
		this.users = users;
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
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

	public Number getLatitude() {
		return latitude;
	}

	public void setLatitude(Number latitude) {
		this.latitude = latitude;
	}

	public Number getLongitude() {
		return longitude;
	}

	public void setLongitude(Number longitude) {
		this.longitude = longitude;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	
}
