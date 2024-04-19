package com.ftninformatika.jwd.modul3.flowrSpot.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.FlowerDTO;

@Component
public class FlowerToFlowerDTO implements Converter<Flower, FlowerDTO> {

	@Override
	public FlowerDTO convert(Flower flower) {

		FlowerDTO flowerDTO = new FlowerDTO();
		flowerDTO.setId(flower.getId());
		flowerDTO.setName(flower.getName());
		flowerDTO.setLatinName(flower.getLatinName());
		flowerDTO.setSightingsNo(flower.getSightingsNo());
		flowerDTO.setProfilePicture(flower.getProfilePicture());
		flowerDTO.setFavorite(flower.isFavorite());
		
		return flowerDTO;
	}
	
	public List<FlowerDTO> convert(List<Flower> flowers){
        List<FlowerDTO> flowersDTO = new ArrayList<>();

        for(Flower f : flowers) {
        	FlowerDTO flowerDTO = convert(f);
        	flowersDTO.add(flowerDTO);
        }

        return flowersDTO;
    }

}
