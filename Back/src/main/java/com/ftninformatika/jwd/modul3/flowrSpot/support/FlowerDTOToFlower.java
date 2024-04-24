package com.ftninformatika.jwd.modul3.flowrSpot.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.FlowerDTO;

@Component
public class FlowerDTOToFlower implements Converter<FlowerDTO, Flower> {

	@Override
	public Flower convert(FlowerDTO flowerDTO) {

		Flower flower = new Flower();
		flower.setId(flowerDTO.getId());
		flower.setName(flowerDTO.getName());
		flower.setLatinName(flowerDTO.getLatinName());
		flower.setSightingsNo(flowerDTO.getSightingsNo());
		flower.setProfilePicture(flowerDTO.getProfilePicture());
		
		return flower;
	}

}
