package com.ftninformatika.jwd.modul3.flowrSpot.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Sighting sighting;

	public Like() {
		super();
	}

	public Like(Long id, User user, Sighting sighting, String userFullName) {
		super();
		this.id = id;
		this.user = user;
		this.sighting = sighting;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sighting getSighting() {
		return sighting;
	}

	public void setSighting(Sighting sighting) {
		this.sighting = sighting;
	}

}
