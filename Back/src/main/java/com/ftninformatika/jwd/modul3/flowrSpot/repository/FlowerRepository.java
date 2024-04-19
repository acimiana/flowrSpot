package com.ftninformatika.jwd.modul3.flowrSpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
	
	Flower findOneById(Long id);
		
	void deleteById(Long id);
	
	List<Flower> findAll();	

}
