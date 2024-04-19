package com.ftninformatika.jwd.modul3.flowrSpot.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserDTO;

@Component
public class UserDTOToUser implements Converter<UserDTO, User> {

	@Override
	public User convert(UserDTO userDTO) {

		User user = new User();
		user.setId(userDTO.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setUsername(userDTO.getUsername());
		
		return user;
	
	}
	
}
