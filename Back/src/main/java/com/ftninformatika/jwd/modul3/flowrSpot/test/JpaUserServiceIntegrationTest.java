package com.ftninformatika.jwd.modul3.flowrSpot.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftninformatika.jwd.modul3.flowrSpot.enumeration.UserRole;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.UserRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaUserServiceIntegrationTest {
	
	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Before
	public void setUp() {
		
		User user1 = new User();
		user1.setFirstName("Alex");
		user1.setLastName("Adams");
		user1.setUsername("aaaaa");
		user1.setUserRole(UserRole.USER);

		Mockito.when(userRepository.findByFirstNameAndLastNameAndUsernameAndUserRole(
				user1.getFirstName(), user1.getLastName(), user1.getUsername(), user1.getUserRole()))
				.thenReturn(user1);
	}

	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		String firstName = "Alex";
		String lastName = "Adams";
		String username = "aaaaa";
		UserRole userRole = UserRole.USER;

		User found = userService.findByFirstNameAndLastNameAndUsernameAndUserRole(firstName, lastName, username,
				userRole);

		assertThat(found).isNotNull();
		assertThat(found.getFirstName()).isEqualTo(firstName);
		assertThat(found.getLastName()).isEqualTo(lastName);
		assertThat(found.getUsername()).isEqualTo(username);
		assertThat(found.getUserRole()).isEqualTo(userRole);
	}

}
