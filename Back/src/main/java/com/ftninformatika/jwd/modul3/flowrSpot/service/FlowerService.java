package com.ftninformatika.jwd.modul3.flowrSpot.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;

public interface FlowerService {
	
	List<Flower> getAll();
	
	Flower findOneById(Long id);
		
	Flower save(Flower flower);
	
	Flower edit(Flower flower);
	
	boolean delete(Long id);
				
}
