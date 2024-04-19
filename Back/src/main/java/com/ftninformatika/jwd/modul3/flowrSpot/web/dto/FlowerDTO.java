package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

public class FlowerDTO {
	
	private Long id;
	
	private String name;
	
	private String latinName;
	
	private Integer sightingsNo;
	
	private String profilePicture;
	
	private boolean favorite;

	public FlowerDTO() {
		super();
	}

	public FlowerDTO(Long id, String name, String latinName, Integer sightingsNo, String profilePicture,
			boolean favorite) {
		super();
		this.id = id;
		this.name = name;
		this.latinName = latinName;
		this.sightingsNo = sightingsNo;
		this.profilePicture = profilePicture;
		this.favorite = favorite;
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

}
