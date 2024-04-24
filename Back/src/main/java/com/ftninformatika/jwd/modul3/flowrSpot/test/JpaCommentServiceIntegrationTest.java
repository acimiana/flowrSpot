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

import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.CommentRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaCommentServiceIntegrationTest {

    @Autowired
    private SightingService sightingService;

    @MockBean
    private CommentRepository commentRepository;

    @Before
    public void setUp() {
        // Create a mock Comment object
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Great sighting!");

        // Mock the behavior of commentRepository.findOneById() method
        Mockito.when(commentRepository.findOneById(1L)).thenReturn(comment);
    }

    @Test
    public void whenValidId_thenCommentShouldBeFound() {
        Long id = 1L;

        // Call the service method to find a comment by ID
        Comment found = sightingService.findOneCommentById(id);

        // Assert that the comment is not null
        assertThat(found).isNotNull();
        // Assert that the comment's ID matches the expected ID
        assertThat(found.getId()).isEqualTo(id);
        // Assert that the comment's content matches the expected content
        assertThat(found.getContent()).isEqualTo("Great sighting!");
    }

}
