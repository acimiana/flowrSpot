package com.ftninformatika.jwd.modul3.flowrSpot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;
import com.ftninformatika.jwd.modul3.flowrSpot.support.CommentDTOToComment;
import com.ftninformatika.jwd.modul3.flowrSpot.support.CommentToCommentDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.support.LikeDTOToLike;
import com.ftninformatika.jwd.modul3.flowrSpot.support.LikeToLikeDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.support.SightingDTOToSighting;
import com.ftninformatika.jwd.modul3.flowrSpot.support.SightingToSightingDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.CommentDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.LikeDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.SightingDTO;

@RestController
@RequestMapping(value = "/api/sightings", produces = MediaType.APPLICATION_JSON_VALUE)
public class SightingController {
	
	@Autowired
	private SightingService sightingService;
	
	@Autowired
	private SightingToSightingDTO toSightingDTO;
	
	@Autowired
	private SightingDTOToSighting toSighting;
	
	@Autowired
	private LikeToLikeDTO toLikeDTO;
	
	@Autowired
	private LikeDTOToLike toLike;
	
	@Autowired
	private CommentToCommentDTO toCommentDTO;
	
	@Autowired
	private CommentDTOToComment toComment;
	
	@GetMapping
    public ResponseEntity<List<SightingDTO>> getAll(){

        List<Sighting> sightings = sightingService.getAll();
        List<SightingDTO> sightingsDTO = toSightingDTO.convert(sightings);

        return new ResponseEntity<>(sightingsDTO, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<SightingDTO> get(@PathVariable Long id){
		
		Sighting sighting = sightingService.findOneById(id);
	        
	    if (sighting == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	                
	    SightingDTO sightingDTO = toSightingDTO.convert(sighting);
	        
	    return new ResponseEntity<>(sightingDTO, HttpStatus.OK);
	    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SightingDTO> create(@Valid @RequestBody SightingDTO sightingDTO){
		
		Sighting sighting = toSighting.convert(sightingDTO);
		Sighting savedSighting= sightingService.save(sighting);
        
		SightingDTO savedSightingDTO = toSightingDTO.convert(savedSighting);

        return new ResponseEntity<>(savedSightingDTO, HttpStatus.CREATED);
    }
	
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SightingDTO> update(@PathVariable Long id, @Valid @RequestBody SightingDTO sightingDTO){

        if(!id.equals(sightingDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Sighting s = sightingService.findOneById(sightingDTO.getId());
        
        if (s == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Sighting sighting = toSighting.convert(sightingDTO);
        Sighting savedSighting= sightingService.edit(sighting);
        SightingDTO sightingUpdated = toSightingDTO.convert(savedSighting);

        return new ResponseEntity<>(sightingUpdated, HttpStatus.OK);
    }
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> delete(@PathVariable Long id){
	     boolean obrisan = sightingService.delete(id);

	     if(obrisan == true) {
	         return new ResponseEntity<>(HttpStatus.OK);
	     } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/{sighting_id}/likes")
    public ResponseEntity<List<LikeDTO>> getAllLikes(@PathVariable Long likeId){

        List<Like> likes = sightingService.findAllLikesBySighting(likeId);
        List<LikeDTO> likesDTO = toLikeDTO.convert(likes);

        return new ResponseEntity<>(likesDTO, HttpStatus.OK);
    }
	
	@PostMapping(value = "/{sighting_id}/likes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikeDTO> create(@Valid @RequestBody LikeDTO likeDTO){
		
		Like like = toLike.convert(likeDTO);
		Like savedLike= sightingService.saveLike(like);
        
		LikeDTO savedLikeDTO = toLikeDTO.convert(savedLike);

        return new ResponseEntity<>(savedLikeDTO, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/{sighting_id}/likes")
	 public ResponseEntity<Void> deleteLike(@PathVariable Long likeId){
	     boolean deleted = sightingService.deleteLike(likeId);

	     if(deleted == true) {
	         return new ResponseEntity<>(HttpStatus.OK);
	     } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/{sighting_id}/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable Long commentId){

        List<Comment> comments = sightingService.findAllCommentsBySighting(commentId);
        List<CommentDTO> commentsDTO = toCommentDTO.convert(comments);

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }
	
	@PostMapping(value = "/{sighting_id}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> create(@Valid @RequestBody CommentDTO commentDTO){
		
		Comment comment = toComment.convert(commentDTO);
		Comment savedComment= sightingService.saveComment(comment);
        
		CommentDTO savedCommentDTO = toCommentDTO.convert(savedComment);

        return new ResponseEntity<>(savedCommentDTO, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/{sighting_id}/comments")
	 public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
	     boolean deleted = sightingService.deleteComment(commentId);

	     if(deleted == true) {
	         return new ResponseEntity<>(HttpStatus.OK);
	     } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
}
