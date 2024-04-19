package com.ftninformatika.jwd.modul3.flowrSpot.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;
import com.ftninformatika.jwd.modul3.flowrSpot.service.UserService;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.LikeDTO;

@Component
public class LikeDTOToLike implements Converter<LikeDTO, Like> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SightingService sightingService;

	@Override
	public Like convert(LikeDTO likeDTO) {
		
		Like like = new Like();
		like.setId(likeDTO.getId());
		like.setUserFullName(likeDTO.getUserFullName());
		
		if (likeDTO.getUserDTO() != null) {
			User user = userService.findOneById(likeDTO.getUserDTO().getId());
			like.setUser(user);
		}
		
		if (likeDTO.getSightingDTO() != null) {
			Sighting sighting = sightingService.findOneById(likeDTO.getSightingDTO().getId());
			like.setSighting(sighting);
		}
		
		return like;
		
	}
	
}
