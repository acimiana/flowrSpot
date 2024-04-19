package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

public class LikeDTO {
	
	private Long id;
	
	private UserDTO userDTO;
	
	private SightingDTO sightingDTO;
	
	private String userFullName;

	public LikeDTO() {
		super();
	}

	public LikeDTO(Long id, UserDTO userDTO, SightingDTO sightingDTO, String userFullName) {
		super();
		this.id = id;
		this.userDTO = userDTO;
		this.sightingDTO = sightingDTO;
		this.userFullName = userFullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public SightingDTO getSightingDTO() {
		return sightingDTO;
	}

	public void setSightingDTO(SightingDTO sightingDTO) {
		this.sightingDTO = sightingDTO;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
}
