package com.ftninformatika.jwd.modul3.flowrSpot.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;

public interface SightingService {
	
	List<Sighting> getAll();
	
	Sighting findOneById(Long id);
	
	Sighting save(Sighting sighting);
	
	Sighting edit(Sighting sighting);
	
	boolean delete(Long id);
	
	List<Sighting> findAllSightingsByFlower(Long id);

	List<Sighting> findAllSightingsByUsers(Long id);
	
	List<Like> findAllLikesBySighting(Long id);
	
	Like saveLike(Like like);
	
	boolean deleteLike(Long id);
	
	List<Comment> findAllCommentsBySighting(Long id);
	
	Comment saveComment(Comment comment);
	
	boolean deleteComment(Long id);

}
