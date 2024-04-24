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

import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.LikeRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaLikeServiceIntegrationTest {

	@Autowired
    private SightingService sightingService;

    @MockBean
    private LikeRepository likeRepository;

    @Before
    public void setUp() {
        // Mock data
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        Sighting sighting = new Sighting();
        sighting.setId(1L);
        sighting.setName("Sighting 1");
        sighting.setDescription("Description 1");

        Like like1 = new Like();
        like1.setId(1L);
        like1.setUser(user);
        like1.setSighting(sighting);

        Like like2 = new Like();
        like2.setId(2L);
        like2.setUser(user);
        like2.setSighting(sighting);

        List<Like> allLikes = new ArrayList<>();
        allLikes.add(like1);
        allLikes.add(like2);

        // Mocking repository methods
        when(likeRepository.findAll()).thenReturn(allLikes);
        when(likeRepository.findOneById(1L)).thenReturn(like1);
        when(likeRepository.findOneById(2L)).thenReturn(like2);
        when(likeRepository.findAllBySightingId(1L)).thenReturn(allLikes);
    }

    @Test
    public void findAllLikesBySightingId(Long sightingId) {
        List<Like> likes = sightingService.findAllLikesBySightingId(sightingId);
        assertThat(likes).hasSize(2);
    }

    @Test
    public void testFindLikeById() {
        Long id = 1L;
        Like like = sightingService.findOneLikeById(id);
        assertThat(like).isNotNull();
        assertThat(like.getId()).isEqualTo(id);
    }

    @Test
    public void testFindAllLikesBySightingId() {
        Long sightingId = 1L;
        List<Like> likes = sightingService.findAllLikesBySightingId(sightingId);
        assertThat(likes).hasSize(2);
        assertThat(likes.get(0).getSighting().getId()).isEqualTo(sightingId);
        assertThat(likes.get(1).getSighting().getId()).isEqualTo(sightingId);
    }
    
}