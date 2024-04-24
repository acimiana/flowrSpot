//package com.ftninformatika.jwd.modul3.flowrSpot.test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import com.ftninformatika.jwd.modul3.flowrSpot.enumeration.UserRole;
//import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
//import com.ftninformatika.jwd.modul3.flowrSpot.repository.UserRepository;
//
//@DataJpaTest
//public class UserRepositoryTest {
//	
//	@Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void testSaveAndFindById() {
//        // Create a User entity and save it
//        User user = new User();
//        user.setFirstName("Alex");
//        user.setLastName("Adams");
//        user.setPassword("aaaaa");
//        user.setUsername("aaaaa");
//        user.setUserRole(UserRole.USER);
//        userRepository.save(user);
//
//        // Find the saved User by id
//        User foundUser = userRepository.findOneById(user.getId());
//
//        // Assert that the found User matches the saved User
//        assertThat(foundUser).isNotNull();
//        assertThat(foundUser.getFirstName()).isEqualTo("Alex");
//        assertThat(foundUser.getLastName()).isEqualTo("Adams");
//        assertThat(foundUser.getPassword()).isEqualTo("aaaaa");
//        assertThat(foundUser.getUsername()).isEqualTo("aaaaa");
//        assertThat(foundUser.getUserRole()).isEqualTo(UserRole.USER);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Create a User entity and save it
//        User user = new User();
//        user.setFirstName("Ben");
//        user.setLastName("Beal");
//        user.setPassword("bbbbb");
//        user.setUsername("bbbbb");
//        user.setUserRole(UserRole.ADMIN);
//        userRepository.save(user);
//
//        // Delete the User by id
//        userRepository.deleteById(user.getId());
//
//        // Assert that the User is deleted
//        assertThat(userRepository.findOneById(user.getId())).isNull();
//    }
//
//    @Test
//    public void testFindAll() {
//        // Save some User entities
//        User user1 = new User();
//        user1.setFirstName("Ben");
//        user1.setLastName("Beal");
//        user1.setPassword("bbbbb");
//        user1.setUsername("bbbbb");
//        user1.setUserRole(UserRole.ADMIN);
//        
//        User user2 = new User();
//        user2.setFirstName("Carl");
//        user2.setLastName("Christie");
//        user2.setPassword("ccccc");
//        user2.setUsername("ccccc");
//        user2.setUserRole(UserRole.USER);
//        
//        userRepository.save(user2);
//
//        // Find all User entities
//        List<User> users = userRepository.findAll();
//
//        // Assert that the repository contains the expected number of users
//        assertThat(users).hasSize(2);
//    }
//
//}
