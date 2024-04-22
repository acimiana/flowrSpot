package com.ftninformatika.jwd.modul3.flowrSpot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String content;
	
//	TODO: obrisati kasnije
	@Column
	private String userFullName;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Sighting sighting;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private User user;
	
	public Comment() {
		super();
	}

	public Comment(Long id, String content, String userFullName, Sighting sighting, User user) {
		super();
		this.id = id;
		this.content = content;
		this.userFullName = userFullName;
		this.sighting = sighting;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public Sighting getSighting() {
		return sighting;
	}

	public void setSighting(Sighting sighting) {
		this.sighting = sighting;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
