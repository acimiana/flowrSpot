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
//import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
//import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
//import com.ftninformatika.jwd.modul3.flowrSpot.repository.SightingRepository;
//
//@DataJpaTest
//public class SightingRepositoryTest {
//
//    @Autowired
//    private SightingRepository sightingRepository;
//
//    @Test
//    public void testSaveAndFindById() {
//        // Create a Flower entity and save it
//        Flower flower = new Flower();
//        flower.setName("Tulip");
//        flower.setLatinName("Tulipa");
//
//        // Create a User entity and save it
//        User user = new User();
//        user.setFirstName("Jane");
//        user.setLastName("Smith");
//
//        // Create a Sighting entity and save it
//        Sighting sighting = new Sighting();
//        sighting.setName("Beautiful Tulip");
//        sighting.setDescription("Found in the park");
//        sighting.setLatitude(40.7128); // Latitude of New York City
//        sighting.setLongitude(-74.0060); // Longitude of New York City
//        sighting.setFlower(flower);
//        sighting.setUser(user);
//        sightingRepository.save(sighting);
//
//        // Find the saved Sighting by id
//        Sighting foundSighting = sightingRepository.findOneById(sighting.getId());
//
//        // Assert that the found Sighting matches the saved Sighting
//        assertThat(foundSighting).isNotNull();
//        assertThat(foundSighting.getName()).isEqualTo("Beautiful Tulip");
//        assertThat(foundSighting.getDescription()).isEqualTo("Found in the park");
//        assertThat(foundSighting.getLatitude()).isEqualTo(40.7128);
//        assertThat(foundSighting.getLongitude()).isEqualTo(-74.0060);
//        assertThat(foundSighting.getFlower()).isEqualTo(flower);
//        assertThat(foundSighting.getUser()).isEqualTo(user);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Create a Sighting entity and save it
//        Sighting sighting = new Sighting();
//        sighting.setName("Wildflower");
//        sighting.setDescription("Discovered on a hike");
//        sighting.setLatitude(37.7749); // Latitude of San Francisco
//        sighting.setLongitude(-122.4194); // Longitude of San Francisco
//        sightingRepository.save(sighting);
//
//        // Delete the Sighting by id
//        sightingRepository.deleteById(sighting.getId());
//
//        // Assert that the Sighting is deleted
//        assertThat(sightingRepository.findOneById(sighting.getId())).isNull();
//    }
//
//    @Test
//    public void testFindAll() {
//        // Save some Sighting entities
//        Flower flower1 = new Flower();
//        flower1.setName("Daisy");
//        flower1.setLatinName("Bellis perennis");
//
//        Flower flower2 = new Flower();
//        flower2.setName("Lily");
//        flower2.setLatinName("Lilium");
//
//        Sighting sighting1 = new Sighting();
//        sighting1.setName("Lovely Daisy");
//        sighting1.setDescription("Seen in the field");
//        sighting1.setLatitude(52.5200); // Latitude of Berlin
//        sighting1.setLongitude(13.4050); // Longitude of Berlin
//        sighting1.setFlower(flower1);
//
//        Sighting sighting2 = new Sighting();
//        sighting2.setName("Beautiful Lily");
//        sighting2.setDescription("Grown in the garden");
//        sighting2.setLatitude(51.5074); // Latitude of London
//        sighting2.setLongitude(-0.1278); // Longitude of London
//        sighting2.setFlower(flower2);
//
//        sightingRepository.save(sighting1);
//        sightingRepository.save(sighting2);
//
//        // Find all Sighting entities
//        List<Sighting> sightings = sightingRepository.findAll();
//
//        // Assert that the repository contains the expected number of sightings
//        assertThat(sightings).hasSize(2);
//    }
//
//    @Test
//    @Sql("/data.sql")
//    public void testFindAllByUserId() {
//        // Test data is loaded from /src/test/resources/data.sql
//        List<Sighting> userSightings = sightingRepository.findAllByUserId(1L);
//
//        // Assert that the repository returns the expected sightings for the user with id 1
//        assertThat(userSightings).hasSize(2);
//        assertThat(userSightings.get(0).getName()).isEqualTo("Beautiful Tulip");
//        assertThat(userSightings.get(1).getName()).isEqualTo("Wildflower");
//    }
//
//    @Test
//    @Sql("/data.sql")
//    public void testFindAllByFlowerId() {
//        // Test data is loaded from /src/test/resources/data.sql
//        List<Sighting> flowerSightings = sightingRepository.findAllByFlowerId(2L);
//
//        // Assert that the repository returns the expected sightings for the flower with id 2
//        assertThat(flowerSightings).hasSize(1);
//        assertThat(flowerSightings.get(0).getName()).isEqualTo("Beautiful Daisy");
//    }
//    
//}