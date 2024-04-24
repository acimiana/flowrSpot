package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

public class LikeDTO {
	
	private Long id;
		
	private SightingDTO sightingDTO;

	public LikeDTO() {
		super();
	}

	public LikeDTO(Long id, SightingDTO sightingDTO) {
		super();
		this.id = id;
		this.sightingDTO = sightingDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SightingDTO getSightingDTO() {
		return sightingDTO;
	}

	public void setSightingDTO(SightingDTO sightingDTO) {
		this.sightingDTO = sightingDTO;
	}

}
