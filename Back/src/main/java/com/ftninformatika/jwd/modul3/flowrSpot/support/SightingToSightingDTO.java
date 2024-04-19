package com.ftninformatika.jwd.modul3.flowrSpot.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.FlowerDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.SightingDTO;

@Component
public class SightingToSightingDTO implements Converter<Sighting, SightingDTO> {
	
	@Autowired
	private FlowerToFlowerDTO toFlowerDTO;

	@Override
	public SightingDTO convert(Sighting sighting) {

		SightingDTO sightingDTO = new SightingDTO();
		sightingDTO.setId(sighting.getId());
		sightingDTO.setName(sighting.getName());
		sightingDTO.setDescription(sighting.getDescription());
		sightingDTO.setLatitude(sighting.getLatitude());
		sightingDTO.setLongitude(sighting.getLongitude());
		
		Flower flower = sighting.getFlower();
		FlowerDTO flowerDTO = toFlowerDTO.convert(flower);
		
		sightingDTO.setFlowerDTO(flowerDTO);
		
		return sightingDTO;
	}
	
	public List<SightingDTO> convert(List<Sighting> sightings){
        List<SightingDTO> sightingsDTO = new ArrayList<>();

        for(Sighting s : sightings) {
        	SightingDTO sightingDTO = convert(s);
        	sightingsDTO.add(sightingDTO);
        }

        return sightingsDTO;
    }

}
