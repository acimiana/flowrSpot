package com.ftninformatika.jwd.modul3.flowrSpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Long> {
	
	List<Sighting> findAll();
	
	Sighting findOneById(Long id);
		
	void deleteById(Long id);
	
	List<Sighting> findAllByFlower(Long id);

	List<Sighting> findAllByUsers(Long id);
	
}
