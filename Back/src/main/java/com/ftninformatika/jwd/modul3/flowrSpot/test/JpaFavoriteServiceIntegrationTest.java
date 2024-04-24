package com.ftninformatika.jwd.modul3.flowrSpot.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Favorite;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.FavoriteRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaFavoriteServiceIntegrationTest {

    @InjectMocks
    private SightingService sightingService;

    @Mock
    private FavoriteRepository favoriteRepository;

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

        Favorite favorite1 = new Favorite();
        favorite1.setId(1L);
        favorite1.setFlower(flower);
        favorite1.setUser(user);

        Favorite favorite2 = new Favorite();
        favorite2.setId(2L);
        favorite2.setFlower(flower);
        favorite2.setUser(user);

        List<Favorite> allFavorites = new ArrayList<>();
        allFavorites.add(favorite1);
        allFavorites.add(favorite2);

        // Mocking repository methods
        when(favoriteRepository.findAll()).thenReturn(allFavorites);
        when(favoriteRepository.findOneById(1L)).thenReturn(favorite1);
        when(favoriteRepository.findOneById(2L)).thenReturn(favorite2);
    }

    @Test
    public void testFindAllFavorites() {
        List<Favorite> favorites = sightingService.getAllFavorites();
        assertThat(favorites).hasSize(2);
    }

    @Test
    public void testFindFavoriteById() {
        Long id = 1L;
        Favorite favorite = sightingService.findOneFavoriteById(id);
        assertThat(favorite).isNotNull();
        assertThat(favorite.getId()).isEqualTo(id);
    }

}
