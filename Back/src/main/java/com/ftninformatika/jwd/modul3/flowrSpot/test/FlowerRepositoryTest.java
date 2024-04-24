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
//import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
//import com.ftninformatika.jwd.modul3.flowrSpot.repository.FlowerRepository;
//
//@DataJpaTest
//public class FlowerRepositoryTest {
//
//    @Autowired
//    private FlowerRepository flowerRepository;
//
//    @Test
//    public void testSaveAndFindById() {
//        // Create a Flower entity and save it
//        Flower flower = new Flower();
//        flower.setName("Rose");
//        flower.setLatinName("Rosa");
//        flowerRepository.save(flower);
//
//        // Find the saved Flower by id
//        Flower foundFlower = flowerRepository.findOneById(flower.getId());
//
//        // Assert that the found Flower matches the saved Flower
//        assertThat(foundFlower).isNotNull();
//        assertThat(foundFlower.getName()).isEqualTo("Rose");
//        assertThat(foundFlower.getLatinName()).isEqualTo("Rosa");
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Create a Flower entity and save it
//        Flower flower = new Flower();
//        flower.setName("Sunflower");
//        flower.setLatinName("Helianthus");
//        flowerRepository.save(flower);
//
//        // Delete the Flower by id
//        flowerRepository.deleteById(flower.getId());
//
//        // Assert that the Flower is deleted
//        assertThat(flowerRepository.findOneById(flower.getId())).isNull();
//    }
//
//    @Test
//    public void testFindAll() {
//        // Save some Flower entities
//        Flower flower1 = new Flower();
//        flower1.setName("Tulip");
//        flower1.setLatinName("Tulipa");
//        flowerRepository.save(flower1);
//
//        Flower flower2 = new Flower();
//        flower2.setName("Daisy");
//        flower2.setLatinName("Bellis perennis");
//        flowerRepository.save(flower2);
//
//        // Find all Flower entities
//        List<Flower> flowers = flowerRepository.findAll();
//
//        // Assert that the repository contains the expected number of flowers
//        assertThat(flowers).hasSize(2);
//    }
//
//    @Test
//    @Sql("/data.sql")
//    public void testFindAllFavoriteFlowersByUserId() {
//        // Test data is loaded from /src/test/resources/data.sql
//        List<Flower> favoriteFlowers = flowerRepository.findAllFavoriteFlowersByUserId(1L);
//
//        // Assert that the repository returns the expected favorite flowers for the user with id 1
//        assertThat(favoriteFlowers).hasSize(2);
//        assertThat(favoriteFlowers.get(0).getName()).isEqualTo("Sunflower");
//        assertThat(favoriteFlowers.get(1).getName()).isEqualTo("Rose");
//    }
//    
//}