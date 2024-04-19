package com.ftninformatika.jwd.modul3.flowrSpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	
	List<Like> findAll();
	
	Like findOneById(Long id);
		
	void deleteById(Long id);
	
	List<Like> findAllBySighting(Long id);
	
}
