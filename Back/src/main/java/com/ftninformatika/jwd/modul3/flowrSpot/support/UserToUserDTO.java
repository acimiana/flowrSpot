package com.ftninformatika.jwd.modul3.flowrSpot.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserDTO;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {
	
	@Override
	public UserDTO convert(User user) {
			
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setFirstName(user.getFirstName());
			userDTO.setLastName(user.getLastName());
			userDTO.setUsername(user.getUsername());
			
			return userDTO;
		}
	
	public List<UserDTO> convert(List<User> users){
		
        List<UserDTO> usersDTO = new ArrayList<>();

        for(User u : users) {
        	UserDTO userDTO = convert(u);
        	usersDTO.add(userDTO);
        }

        return usersDTO;
    }
	
}
