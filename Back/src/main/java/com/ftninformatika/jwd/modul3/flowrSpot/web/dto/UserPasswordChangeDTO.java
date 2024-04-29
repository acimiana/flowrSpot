package com.ftninformatika.jwd.modul3.flowrSpot.web.dto;

import javax.validation.constraints.Pattern;

public class UserPasswordChangeDTO {
	
	private String username;

    private String oldPassword;

    @Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$")
    private String password;

    private String repeatedPassword;

	public UserPasswordChangeDTO() {
		super();
	}

	public UserPasswordChangeDTO(String username, String oldPassword,
			@Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$") String password,
			String repeatedPassword) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
		this.password = password;
		this.repeatedPassword = repeatedPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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

}
