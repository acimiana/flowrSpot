package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

public class SightingDTO {
	
	private Long id;
	
	private FlowerDTO flowerDTO;
	
	private String name;
	
	private String description;
	
	private Number latitude;
	
	private Number longitude;

	public SightingDTO() {
		super();
	}

	public SightingDTO(Long id, FlowerDTO flowerDTO, String name, String description, Number latitude,
			Number longitude) {
		super();
		this.id = id;
		this.flowerDTO = flowerDTO;
		this.name = name;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FlowerDTO getFlowerDTO() {
		return flowerDTO;
	}

	public void setFlowerDTO(FlowerDTO flowerDTO) {
		this.flowerDTO = flowerDTO;
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
	
}
