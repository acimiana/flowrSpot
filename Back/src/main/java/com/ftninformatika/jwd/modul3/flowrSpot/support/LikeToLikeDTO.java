package com.ftninformatika.jwd.modul3.flowrSpot.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.LikeDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.SightingDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserDTO;

@Component
public class LikeToLikeDTO implements Converter<Like, LikeDTO> {
	
	@Autowired
	private UserToUserDTO toUserDTO;
	
	@Autowired
	private SightingToSightingDTO toSightingDTO;

	@Override
	public LikeDTO convert(Like like) {
		
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setId(like.getId());
		
		User user = like.getUser();
		UserDTO userDTO = toUserDTO.convert(user);
		
		likeDTO.setUserDTO(userDTO);
		
		Sighting sighting = like.getSighting();
		SightingDTO sightingDTO = toSightingDTO.convert(sighting);
		
		likeDTO.setSightingDTO(sightingDTO);
		
		return likeDTO;
	}
	
	public List<LikeDTO> convert(List<Like> likes){
        List<LikeDTO> likesDTO = new ArrayList<>();

        for(Like l : likes) {
        	LikeDTO likeDTO = convert(l);
        	likesDTO.add(likeDTO);
        }

        return likesDTO;
    }

}
