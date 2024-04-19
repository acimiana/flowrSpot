package com.ftninformatika.jwd.modul3.flowrSpot.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.CommentDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.SightingDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserDTO;

@Component
public class CommentToCommentDTO implements Converter<Comment, CommentDTO> {
	
	@Autowired
	private UserToUserDTO toUserDTO;
	
	@Autowired
	private SightingToSightingDTO toSightingDTO;

	@Override
	public CommentDTO convert(Comment comment) {

		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setUserFullName(comment.getUserFullName());
		commentDTO.setContent(comment.getContent());
		
		User user = comment.getUser();
		UserDTO userDTO = toUserDTO.convert(user);
		
		Sighting sighting = comment.getSighting();
		SightingDTO sightingDTO = toSightingDTO.convert(sighting);
		
		commentDTO.setUserDTO(userDTO);
		commentDTO.setSightingDTO(sightingDTO);
		
		return commentDTO;
	}
	
	public List<CommentDTO> convert(List<Comment> comments){
        List<CommentDTO> commentsDTO = new ArrayList<>();

        for(Comment c : comments) {
        	CommentDTO commentDTO = convert(c);
        	commentsDTO.add(commentDTO);
        }

        return commentsDTO;
    }

	

}
