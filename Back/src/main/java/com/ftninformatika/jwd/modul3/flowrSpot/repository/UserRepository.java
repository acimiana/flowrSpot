package com.ftninformatika.jwd.modul3.flowrSpot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.flowrSpot.enumeration.UserRole;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findOneById(Long id);
	
	List<User> findAll();
	
	Optional<User> findFirstByUsername(String username);

    Optional<User> findFirstByUsernameAndPassword(String username, String password);

	User findByFirstNameAndLastNameAndUsernameAndUserRole(String firstName, String lastName, String username, UserRole userRole);

}
