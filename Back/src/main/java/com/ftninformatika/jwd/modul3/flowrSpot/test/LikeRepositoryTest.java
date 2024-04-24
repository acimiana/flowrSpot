//package com.ftninformatika.jwd.modul3.flowrSpot.test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.jdbc.Sql;
//
//import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;
//import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
//import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
//import com.ftninformatika.jwd.modul3.flowrSpot.repository.LikeRepository;
//
//@DataJpaTest
//public class LikeRepositoryTest {
//
//    @Autowired
//    private LikeRepository likeRepository;
//
//    @Test
//    public void testSaveAndFindById() {
//        // Create a User entity and save it
//        User user = new User();
//        user.setFirstName("John");
//        user.setLastName("Doe");
//
//        // Create a Sighting entity and save it
//        Sighting sighting = new Sighting();
//        sighting.setName("Beautiful Flower");
//        sighting.setDescription("Found in the garden");
//
//        // Create a Like entity and save it
//        Like like = new Like();
//        like.setUser(user);
//        like.setSighting(sighting);
//        likeRepository.save(like);
//
//        // Find the saved Like by id
//        Like foundLike = likeRepository.findOneById(like.getId());
//
//        // Assert that the found Like matches the saved Like
//        assertThat(foundLike).isNotNull();
//        assertThat(foundLike.getUser()).isEqualTo(user);
//        assertThat(foundLike.getSighting()).isEqualTo(sighting);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Create a Like entity and save it
//        Like like = new Like();
//        likeRepository.save(like);
//
//        // Delete the Like by id
//        likeRepository.deleteById(like.getId());
//
//        // Assert that the Like is deleted
//        assertThat(likeRepository.findOneById(like.getId())).isNull();
//    }
//
//    @Test
//    public void testFindAll() {
//        // Save some Like entities
//        Like like1 = new Like();
//        Like like2 = new Like();
//
//        likeRepository.save(like1);
//        likeRepository.save(like2);
//
//        // Find all Like entities
//        List<Like> likes = likeRepository.findAll();
//
//        // Assert that the repository contains the expected number of likes
//        assertThat(likes).hasSize(2);
//    }
//
//    @Test
//    @Sql("/data.sql")
//    public void testFindAllBySightingId() {
//        // Test data is loaded from /src/test/resources/data.sql
//        List<Like> likes = likeRepository.findAllBySightingId(1L);
//
//        // Assert that the repository returns the expected likes for the sighting with id 1
//        assertThat(likes).hasSize(2);
//        assertThat(likes.get(0).getUser().getFirstName()).isEqualTo("Alice");
//        assertThat(likes.get(1).getUser().getFirstName()).isEqualTo("Bob");
//    }
//}
