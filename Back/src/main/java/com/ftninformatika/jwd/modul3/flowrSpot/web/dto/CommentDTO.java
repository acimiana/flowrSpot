package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

public class CommentDTO {
	
	private Long id;
	
	private String content;
		
	private UserDTO userDTO;
	
	private SightingDTO sightingDTO;

	public CommentDTO() {
		super();
	}

	public CommentDTO(Long id, String content, UserDTO userDTO, SightingDTO sightingDTO) {
		super();
		this.id = id;
		this.content = content;
		this.userDTO = userDTO;
		this.sightingDTO = sightingDTO;
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

}
