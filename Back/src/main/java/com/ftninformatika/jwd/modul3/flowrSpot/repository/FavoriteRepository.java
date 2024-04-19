package com.ftninformatika.jwd.modul3.flowrSpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
	
	List<Favorite> findAll();
	
	Favorite findOneById(Long id);
	

}
