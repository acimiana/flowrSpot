package com.ftninformatika.jwd.modul3.flowrSpot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Comment;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Like;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.security.TokenUtils;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;
import com.ftninformatika.jwd.modul3.flowrSpot.support.CommentDTOToComment;
import com.ftninformatika.jwd.modul3.flowrSpot.support.CommentToCommentDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.support.LikeDTOToLike;
import com.ftninformatika.jwd.modul3.flowrSpot.support.LikeToLikeDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.support.SightingDTOToSighting;
import com.ftninformatika.jwd.modul3.flowrSpot.support.SightingToSightingDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.support.UserDTOToUser;
import com.ftninformatika.jwd.modul3.flowrSpot.support.UserToUserDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.CommentDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.LikeDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.SightingDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserDTO;

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
	private UserDTOToUser toUser;
	
	@Autowired
	private UserToUserDTO toUserDTO;
	
	@Autowired
	private CommentToCommentDTO toCommentDTO;
	
	@Autowired
	private CommentDTOToComment toComment;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@PreAuthorize("permitAll()")
	@GetMapping
    public ResponseEntity<List<SightingDTO>> getAll(){

        List<Sighting> sightings = sightingService.getAll();
        List<SightingDTO> sightingsDTO = toSightingDTO.convert(sightings);

        return new ResponseEntity<>(sightingsDTO, HttpStatus.OK);
    }
	
	@PreAuthorize("permitAll()")
	@GetMapping("/{id}")
	public ResponseEntity<SightingDTO> get(@PathVariable Long id){
		
		Sighting sighting = sightingService.findOneById(id);
	        
	    if (sighting == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	                
	    SightingDTO sightingDTO = toSightingDTO.convert(sighting);
	        
	    return new ResponseEntity<>(sightingDTO, HttpStatus.OK);
	    }
	
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SightingDTO> create(@RequestHeader (name="Authorization") String token, @Valid @RequestBody SightingDTO sightingDTO){
		
//		String username = tokenUtils.getUsernameFromToken(token);
//		
//		System.out.println("USERNAME");
//		System.out.println(username);
//		
//		if(!username.equals(sightingDTO.getUserDTO().getUsername())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		
		Sighting sighting = toSighting.convert(sightingDTO);
		Sighting savedSighting= sightingService.save(sighting);
        
		SightingDTO savedSightingDTO = toSightingDTO.convert(savedSighting);

        return new ResponseEntity<>(savedSightingDTO, HttpStatus.CREATED);
    }
	
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SightingDTO> update(@RequestHeader (name="Authorization") String token, @PathVariable Long id, @Valid @RequestBody SightingDTO sightingDTO){

//		String username = tokenUtils.getUsernameFromToken(token);
//		
//		if(!username.equals(sightingDTO.getUserDTO().getUsername())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
				
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
	
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@RequestHeader (name="Authorization") String token, @PathVariable Long id){
	     
//		String username = tokenUtils.getUsernameFromToken(token);
//		
//		Sighting sighting = sightingService.findOneById(id);
//		
//		if(!username.equals(sighting.getUser().getUsername())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		
		boolean obrisan = sightingService.delete(id);

	     if(obrisan == true) {
	         return new ResponseEntity<>(HttpStatus.OK);
	     } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("/{sighting_id}/likes")
    public ResponseEntity<List<LikeDTO>> getAllLikes(@PathVariable Long sighting_id){
		
		System.out.println("ISPISUJEM");
		System.out.println(sighting_id);

        List<Like> likes = sightingService.findAllLikesBySightingId(sighting_id);
        List<LikeDTO> likesDTO = toLikeDTO.convert(likes);

        return new ResponseEntity<>(likesDTO, HttpStatus.OK);
    }
	
//	_____________________________________________________________________________
//								
//									NE RADI
//	_____________________________________________________________________________
	
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping(value = "/{sighting_id}/likes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LikeDTO> createLike(@RequestHeader (name="Authorization") String token, @Valid @RequestBody LikeDTO likeDTO){
		
//		String username = tokenUtils.getUsernameFromToken(token);
//		
//		if(!username.equals(likeDTO.getUserDTO().getUsername())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		
		System.out.println("LIKE DTO");
		System.out.println(likeDTO);
		
		UserDTO userDTO = likeDTO.getUserDTO();
		System.out.println("USER DTO");
		System.out.println(userDTO);
		
		SightingDTO sightingDTO = likeDTO.getSightingDTO();
		System.out.println("SIGHTING DTO");
		System.out.println(sightingDTO);
		
		Like like = toLike.convert(likeDTO);
		
		System.out.println("LIKE");
		System.out.println(like);
		
		User userIzLajka = like.getUser();
		System.out.println("USER IZ LAJKA");
		System.out.println(userIzLajka);
		
		User userConvert = toUser.convert(userDTO);
		System.out.println("USER CONVERT");
		System.out.println(userConvert);
		
		Sighting sightingIzLajka = like.getSighting();
		System.out.println("SIGHTING IZ LAJKA");
		System.out.println(sightingIzLajka);
		
		Sighting sightingConvert = toSighting.convert(sightingDTO);
		System.out.println("SIGHTING CONVERT");
		System.out.println(sightingConvert);
				
		Like savedLike= sightingService.saveLike(like);
		
		System.out.println("SAVED LIKE");
		System.out.println(savedLike);
        
		LikeDTO savedLikeDTO = toLikeDTO.convert(savedLike);
		
		System.out.println("SAVED LIKEDTO");
		System.out.println("savedLikeDTO");

        return new ResponseEntity<>(savedLikeDTO, HttpStatus.CREATED);
    }
	
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@DeleteMapping("/{sighting_id}/likes/{likeId}")
	 public ResponseEntity<Void> deleteLike(@RequestHeader (name="Authorization") String token, @PathVariable Long likeId){
		
//		String username = tokenUtils.getUsernameFromToken(token);
//		
//		Like like = sightingService.findOneLikeById(likeId);
//		
//		if(!username.equals(like.getUser().getUsername())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		
	     boolean deleted = sightingService.deleteLike(likeId);

	     if(deleted == true) {
	         return new ResponseEntity<>(HttpStatus.OK);
	     } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@PreAuthorize("permitAll()")
	@GetMapping("/{sighting_id}/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable Long sighting_id){

        List<Comment> comments = sightingService.findAllCommentsBySightingId(sighting_id);
        List<CommentDTO> commentsDTO = toCommentDTO.convert(comments);

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }
	
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping(value = "/{sighting_id}/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> create(@RequestHeader (name="Authorization") String token, @Valid @RequestBody CommentDTO commentDTO){
		
//		String username = tokenUtils.getUsernameFromToken(token);
//		
//		if(!username.equals(commentDTO.getUserDTO().getUsername())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		
		Comment comment = toComment.convert(commentDTO);
		Comment savedComment= sightingService.saveComment(comment);
        
		CommentDTO savedCommentDTO = toCommentDTO.convert(savedComment);

        return new ResponseEntity<>(savedCommentDTO, HttpStatus.CREATED);
    }
	
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@DeleteMapping("/{sighting_id}/comments/{commentId}")
	 public ResponseEntity<Void> deleteComment(@RequestHeader (name="Authorization") String token, @PathVariable Long commentId){
		
//		String username = tokenUtils.getUsernameFromToken(token);
//		
//		Comment comment = sightingService.findOneCommentById(commentId);
//		
//		if(!username.equals(comment.getUser().getUsername())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
		
	     boolean deleted = sightingService.deleteComment(commentId);

	     if(deleted == true) {
	         return new ResponseEntity<>(HttpStatus.OK);
	     } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}	
}
