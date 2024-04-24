package com.ftninformatika.jwd.modul3.flowrSpot.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.SightingRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaSightingServiceIntegrationTest {

    @Autowired
    private SightingService sightingService;

    @MockBean
    private SightingRepository sightingRepository;

    @Before
    public void setUp() {
        // Mock data
        Flower flower = new Flower();
        flower.setId(1L);
        flower.setName("Sunflower");
        flower.setLatinName("Helianthus annuus");

        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        Sighting sighting1 = new Sighting();
        sighting1.setId(1L);
        sighting1.setName("Sighting 1");
        sighting1.setDescription("Description 1");
        sighting1.setFlower(flower);
        sighting1.setUser(user);

        Sighting sighting2 = new Sighting();
        sighting2.setId(2L);
        sighting2.setName("Sighting 2");
        sighting2.setDescription("Description 2");
        sighting2.setFlower(flower);
        sighting2.setUser(user);

        List<Sighting> allSightings = new ArrayList<>();
        allSightings.add(sighting1);
        allSightings.add(sighting2);

        // Mocking repository methods
        when(sightingRepository.findAll()).thenReturn(allSightings);
        when(sightingRepository.findOneById(1L)).thenReturn(sighting1);
        when(sightingRepository.findAllByFlowerId(1L)).thenReturn(allSightings);
        when(sightingRepository.findAllByUserId(1L)).thenReturn(allSightings);
    }

    @Test
    public void testFindAllSightings() {
        List<Sighting> sightings = sightingService.getAll();
        assertThat(sightings).hasSize(2);
    }

    @Test
    public void testFindSightingById() {
        Long id = 1L;
        Sighting sighting = sightingService.findOneById(id);
        assertThat(sighting).isNotNull();
        assertThat(sighting.getId()).isEqualTo(id);
    }

    @Test
    public void testFindAllSightingsByFlowerId() {
        Long flowerId = 1L;
        List<Sighting> sightings = sightingService.findAllSightingsByFlowerId(flowerId);
        assertThat(sightings).hasSize(2);
        assertThat(sightings.get(0).getFlower().getId()).isEqualTo(flowerId);
        assertThat(sightings.get(1).getFlower().getId()).isEqualTo(flowerId);
    }

    @Test
    public void testFindAllSightingsByUserId() {
        Long userId = 1L;
        List<Sighting> sightings = sightingService.findAllSightingsByUserId(userId);
        assertThat(sightings).hasSize(2);
        assertThat(sightings.get(0).getUser().getId()).isEqualTo(userId);
        assertThat(sightings.get(1).getUser().getId()).isEqualTo(userId);
    }
    
}
