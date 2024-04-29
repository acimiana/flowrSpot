package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

public class LikeDTO {
	
	private Long id;
		
	private SightingDTO sightingDTO;
	
	private UserDTO userDTO;

	public LikeDTO() {
		super();
	}

	public LikeDTO(Long id, SightingDTO sightingDTO, UserDTO userDTO) {
		super();
		this.id = id;
		this.sightingDTO = sightingDTO;
		this.userDTO = userDTO;
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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	@Override
	public String toString() {
		return "LikeDTO [id=" + id + ", sightingDTO=" + sightingDTO + ", userDTO=" + userDTO + "]";
	}

}
