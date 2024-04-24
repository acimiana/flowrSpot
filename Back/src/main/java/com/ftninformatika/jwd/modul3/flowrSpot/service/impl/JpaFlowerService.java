package com.ftninformatika.jwd.modul3.flowrSpot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.FlowerRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.FlowerService;

@Service
public class JpaFlowerService implements FlowerService {
	
	@Autowired
	private FlowerRepository flowerRepository;

	@Override
	public List<Flower> getAll() {
		return flowerRepository.findAll();
	}

	@Override
	public Flower findOneById(Long id) {
		return flowerRepository.findOneById(id);
	}
	
	@Override
	public Flower save(Flower flower) {
		return flowerRepository.save(flower);
	}

	@Override
	public Flower edit(Flower flower) {
		Flower flowerUpdated= flowerRepository.save(flower);
        return flowerUpdated;
	}

	@Override
	public boolean delete(Long id) {

		Flower f = flowerRepository.findOneById(id);
		if (f == null) {
			return false;
		}
		
		flowerRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Flower> findAllFavoriteFlowersByUserId(Long myId) {
		return flowerRepository.findAllFavoriteFlowersByUserId(myId);
	}

	@Override
	public Flower findByNameAndLatinName(String name, String latinName) {
		return flowerRepository.findByNameAndLatinName(name, latinName);
	}

}
