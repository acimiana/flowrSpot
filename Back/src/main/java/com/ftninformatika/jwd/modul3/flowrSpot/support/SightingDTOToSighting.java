package com.ftninformatika.jwd.modul3.flowrSpot.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.service.FlowerService;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.SightingDTO;

@Component
public class SightingDTOToSighting implements Converter<SightingDTO, Sighting> {
	
	@Autowired
	private FlowerService flowerService;

	@Override
	public Sighting convert(SightingDTO sightingDTO) {

		Sighting sighting = new Sighting();
		sighting.setId(sightingDTO.getId());
		sighting.setName(sightingDTO.getName());
		sighting.setDescription(sightingDTO.getDescription());
		sighting.setLatitude(sightingDTO.getLatitude());
		sighting.setLongitude(sightingDTO.getLongitude());
		
		if (sightingDTO.getFlowerDTO() != null) {
			Flower flower = flowerService.findOneById(sightingDTO.getFlowerDTO().getId());
			sighting.setFlower(flower);
		}
		
		return sighting;
	}

}