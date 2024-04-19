package com.ftninformatika.jwd.modul3.flowrSpot.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;
import com.ftninformatika.jwd.modul3.flowrSpot.service.UserService;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.CommentDTO;

@Component
public class CommentDTOToComment implements Converter<CommentDTO, Comment> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SightingService sightingService;

	@Override
	public Comment convert(CommentDTO commentDTO) {
		
		Comment comment = new Comment();
		comment.setId(commentDTO.getId());
		comment.setUserFullName(commentDTO.getUserFullName());
		comment.setContent(commentDTO.getContent());
		
		
		if (commentDTO.getUserDTO() != null) {
			User user = userService.findOneById(commentDTO.getUserDTO().getId());
			comment.setUser(user);
		}
		
		if (commentDTO.getSightingDTO() != null) {
			Sighting sighting = sightingService.findOneById(commentDTO.getSightingDTO().getId());
			comment.setSighting(sighting);
		}
		
		return comment;
	}

}
