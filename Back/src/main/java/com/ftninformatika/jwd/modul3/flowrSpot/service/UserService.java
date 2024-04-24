package com.ftninformatika.jwd.modul3.flowrSpot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.flowrSpot.enumeration.UserRole;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserPasswordChangeDTO;

public interface UserService {
	
	User findOneById(Long id);
	
	List<User> findAll();
	
	User save(User user);
	
	Page<User> findAll(int pageNo);
	
	boolean changePassword(Long id, UserPasswordChangeDTO userPasswordChangeDTO);
	
	Optional<User> findOne(Long id);
	
	void delete(Long id);

    Optional<User> findbyUsername(String username);
    
    boolean validateUser(String username);
    
    int add(int a, int b);
    
	User findByFirstNameAndLastNameAndUsernameAndUserRole(String firstName, String lastName, String username, UserRole userRole);
	
	User updateUser(User user);

}
