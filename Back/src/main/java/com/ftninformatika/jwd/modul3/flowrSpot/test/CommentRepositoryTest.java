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
//import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;
//import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
//import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
//import com.ftninformatika.jwd.modul3.flowrSpot.repository.CommentRepository;
//
//@DataJpaTest
//public class CommentRepositoryTest {
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Test
//    public void testSaveAndFindById() {
//        // Create a Sighting entity and save it
//        Sighting sighting = new Sighting();
//        sighting.setName("Beautiful Rose");
//        sighting.setDescription("Found in the garden");
//
//        // Create a User entity and save it
//        User user = new User();
//        user.setFirstName("Jane");
//        user.setLastName("Doe");
//
//        // Create a Comment entity and save it
//        Comment comment = new Comment();
//        comment.setContent("Lovely flower!");
//        comment.setSighting(sighting);
//        comment.setUser(user);
//        commentRepository.save(comment);
//
//        // Find the saved Comment by id
//        Comment foundComment = commentRepository.findOneById(comment.getId());
//
//        // Assert that the found Comment matches the saved Comment
//        assertThat(foundComment).isNotNull();
//        assertThat(foundComment.getContent()).isEqualTo("Lovely flower!");
//        assertThat(foundComment.getSighting()).isEqualTo(sighting);
//        assertThat(foundComment.getUser()).isEqualTo(user);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Create a Comment entity and save it
//        Comment comment = new Comment();
//        comment.setContent("Amazing sighting!");
//        commentRepository.save(comment);
//
//        // Delete the Comment by id
//        commentRepository.deleteById(comment.getId());
//
//        // Assert that the Comment is deleted
//        assertThat(commentRepository.findOneById(comment.getId())).isNull();
//    }
//
//    @Test
//    public void testFindAll() {
//        // Save some Comment entities
//        Comment comment1 = new Comment();
//        comment1.setContent("Great photo!");
//        Comment comment2 = new Comment();
//        comment2.setContent("Interesting description!");
//
//        commentRepository.save(comment1);
//        commentRepository.save(comment2);
//
//        // Find all Comment entities
//        List<Comment> comments = commentRepository.findAll();
//
//        // Assert that the repository contains the expected number of comments
//        assertThat(comments).hasSize(2);
//    }
//
//    @Test
//    @Sql("/data.sql")
//    public void testFindAllBySightingId() {
//        // Test data is loaded from /src/test/resources/data.sql
//        List<Comment> comments = commentRepository.findAllBySightingId(1L);
//
//        // Assert that the repository returns the expected comments for the sighting with id 1
//        assertThat(comments).hasSize(2);
//        assertThat(comments.get(0).getContent()).isEqualTo("Nice photo!");
//        assertThat(comments.get(1).getContent()).isEqualTo("Beautiful flower!");
//    }
//    
//}
