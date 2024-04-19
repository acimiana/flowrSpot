package com.ftninformatika.jwd.modul3.flowrSpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findAll();
	
	Comment findOneById(Long id);
		
	void deleteById(Long id);
	
	List<Comment> findAllBySighting(Long id);

}
