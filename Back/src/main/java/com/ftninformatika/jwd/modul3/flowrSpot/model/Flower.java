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
import javax.persistence.OneToMany;

@Entity
public class Flower {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String latinName;
	
	@Column
	private Integer sightingsNo;
	
	@Column
	private String profilePicture;
	
	@Column
	private boolean favorite;
	
	@OneToMany(mappedBy = "flower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sighting> sightings = new ArrayList<>();
	
	@OneToMany(mappedBy = "flower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favorite> favorites = new ArrayList<>();

	public Flower() {
		super();
	}

	public Flower(Long id, String name, String latinName, Integer sightingsNo, String profilePicture,
			boolean favorite, List<Sighting> sightings, List<Favorite> favorites) {
		super();
		this.id = id;
		this.name = name;
		this.latinName = latinName;
		this.sightingsNo = sightingsNo;
		this.profilePicture = profilePicture;
		this.favorite = favorite;
		this.sightings = sightings;
		this.favorites = favorites;
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

	public String getLatinName() {
		return latinName;
	}

	public void setLatinName(String latinName) {
		this.latinName = latinName;
	}

	public Integer getSightingsNo() {
		return sightingsNo;
	}

	public void setSightingsNo(Integer sightingsNo) {
		this.sightingsNo = sightingsNo;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public List<Sighting> getSightings() {
		return sightings;
	}

	public void setSightings(List<Sighting> sightings) {
		this.sightings = sightings;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
	
}