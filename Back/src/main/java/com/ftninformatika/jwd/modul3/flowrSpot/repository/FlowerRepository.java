package com.ftninformatika.jwd.modul3.flowrSpot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
	
	Flower findOneById(Long id);
		
	void deleteById(Long id);
	
	List<Flower> findAll();
	
	@Query("SELECT f FROM Flower f " + "JOIN f.favorites fav " + "WHERE fav.user.id = :myId")
	List<Flower> findAllFavoriteFlowersByUserId(Long myId);
	
	Flower findByNameAndLatinName(String name, String latinName);

}
