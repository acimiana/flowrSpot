package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

public class UserRegistrationDTO extends UserDTO {
	
	private String password;
	
	private String repeatedPassword;
	
	private String username;

	public UserRegistrationDTO() {
		super();
	}

	public UserRegistrationDTO(String password, String repeatedPassword, String username) {
		super();
		this.password = password;
		this.repeatedPassword = repeatedPassword;
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
