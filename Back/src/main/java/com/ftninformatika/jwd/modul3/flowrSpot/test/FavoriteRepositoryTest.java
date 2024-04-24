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
//import com.ftninformatika.jwd.modul3.flowrSpot.model.Favorite;
//import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
//import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
//import com.ftninformatika.jwd.modul3.flowrSpot.repository.FavoriteRepository;
//
//@DataJpaTest
//public class FavoriteRepositoryTest {
//
//    @Autowired
//    private FavoriteRepository favoriteRepository;
//
//    @Test
//    public void testSaveAndFindById() {
//        // Create a Flower entity and save it
//        Flower flower = new Flower();
//        flower.setName("Sunflower");
//        flower.setLatinName("Helianthus annuus");
//
//        // Create a User entity and save it
//        User user = new User();
//        user.setFirstName("Alex");
//        user.setLastName("Adams");
//
//        // Create a Favorite entity and save it
//        Favorite favorite = new Favorite();
//        favorite.setFlower(flower);
//        favorite.setUser(user);
//        favoriteRepository.save(favorite);
//
//        // Find the saved Favorite by id
//        Favorite foundFavorite = favoriteRepository.findOneById(favorite.getId());
//
//        // Assert that the found Favorite matches the saved Favorite
//        assertThat(foundFavorite).isNotNull();
//        assertThat(foundFavorite.getFlower()).isEqualTo(flower);
//        assertThat(foundFavorite.getUser()).isEqualTo(user);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Create a Favorite entity and save it
//        Favorite favorite = new Favorite();
//        favoriteRepository.save(favorite);
//
//        // Delete the Favorite by id
//        favoriteRepository.deleteById(favorite.getId());
//
//        // Assert that the Favorite is deleted
//        assertThat(favoriteRepository.findOneById(favorite.getId())).isNull();
//    }
//
//    @Test
//    public void testFindAll() {
//        // Save some Favorite entities
//        Favorite favorite1 = new Favorite();
//        Favorite favorite2 = new Favorite();
//
//        favoriteRepository.save(favorite1);
//        favoriteRepository.save(favorite2);
//
//        // Find all Favorite entities
//        List<Favorite> favorites = favoriteRepository.findAll();
//
//        // Assert that the repository contains the expected number of favorites
//        assertThat(favorites).hasSize(2);
//    }
//}
