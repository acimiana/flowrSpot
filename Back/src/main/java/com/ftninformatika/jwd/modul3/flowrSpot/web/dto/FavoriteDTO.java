package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

public class FavoriteDTO {
	
	private Long id;
	
	private FlowerDTO flowerDTO;
	
	private UserDTO userDTO;

	public FavoriteDTO() {
		super();
	}

	public FavoriteDTO(Long id, FlowerDTO flowerDTO, UserDTO userDTO) {
		super();
		this.id = id;
		this.flowerDTO = flowerDTO;
		this.userDTO = userDTO;
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

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
