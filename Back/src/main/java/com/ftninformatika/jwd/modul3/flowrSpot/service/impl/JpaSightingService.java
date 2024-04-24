package com.ftninformatika.jwd.modul3.flowrSpot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Favorite;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.CommentRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.FavoriteRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.FlowerRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.LikeRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.SightingRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;

@Service
public class JpaSightingService implements SightingService {
	
	@Autowired
	private SightingRepository sightingRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Autowired
	private FlowerRepository flowerRepository;
	
	@Override
	public List<Sighting> getAll() {
		return sightingRepository.findAll();
	}
	
	@Override
	public Sighting findOneById(Long id) {
		return sightingRepository.findOneById(id);
	}

	@Override
	public Sighting save(Sighting sighting) {
//		currentFlower = flowerRepository.findOneById(sighting.getFlower().getId());
		Flower currentFlower = sighting.getFlower();
		currentFlower.setSightingsNo(currentFlower.getSightingsNo() + 1);
		flowerRepository.save(currentFlower);
//		NE MORA, CUVA SAMO FLOWER_ID
//		sighting.setFlower(currentFlower);
		return sightingRepository.save(sighting);
	}
	
	@Override
	public Sighting edit(Sighting sighting) {
		Sighting sightingUpdated= sightingRepository.save(sighting);
        return sightingUpdated;
	}
	
	@Override
	public boolean delete(Long id) {

		Sighting s = sightingRepository.findOneById(id);
		if (s == null) {
			return false;
		}
		
		sightingRepository.deleteById(id);
		return true;	
	}
	
	@Override
	public List<Sighting> findAllSightingsByFlowerId(Long id) {
		return sightingRepository.findAllByFlowerId(id);
	}

	@Override
	public List<Sighting> findAllSightingsByUserId(Long id) {
		return sightingRepository.findAllByUserId(id);
	}

	@Override
	public List<Like> findAllLikesBySightingId(Long id) {
		return likeRepository.findAllBySightingId(id);
	}

	@Override
	public Like findOneLikeById(Long id) {
		return likeRepository.findOneById(id);
	}

	@Override
	public Like saveLike(Like like) {
		return likeRepository.save(like);
	}

	@Override
	public boolean deleteLike(Long id) {

		Like l = likeRepository.findOneById(id);
		if (l == null) {
			return false;
		}
		
		likeRepository.deleteById(id);
		return true;
		
	}

	@Override
	public List<Comment> findAllCommentsBySightingId(Long id) {
		return commentRepository.findAllBySightingId(id);
	}

	@Override
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public boolean deleteComment(Long id) {

		Comment c = commentRepository.findOneById(id);
		if (c == null) {
			return false;
		}
		
		commentRepository.deleteById(id);
		return true;
	}

	@Override
	public Comment findOneCommentById(Long id) {
		return commentRepository.findOneById(id);
	}

	@Override
	public List<Favorite> getAllFavorites() {
		return favoriteRepository.findAll();
	}

	@Override
	public Favorite findOneFavoriteById(Long id) {
		return favoriteRepository.findOneById(id);
	}

}
