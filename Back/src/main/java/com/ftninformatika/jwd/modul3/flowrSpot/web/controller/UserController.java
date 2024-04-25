package com.ftninformatika.jwd.modul3.flowrSpot.web.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.model.User;
import com.ftninformatika.jwd.modul3.flowrSpot.security.TokenUtils;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;
import com.ftninformatika.jwd.modul3.flowrSpot.service.UserService;
import com.ftninformatika.jwd.modul3.flowrSpot.support.SightingToSightingDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.support.UserDTOToUser;
import com.ftninformatika.jwd.modul3.flowrSpot.support.UserToUserDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.AuthUserDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.SightingDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserPasswordChangeDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
    private TokenUtils tokenUtils;
	
	@Autowired
	private SightingService sightingService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDTO toUserDTO;
	
	@Autowired
	private UserDTOToUser toUser;
	
	@Autowired
	private SightingToSightingDTO toSightingDTO;
	
	@PreAuthorize("permitAll()")
	@GetMapping
    public ResponseEntity<List<UserDTO>> get(@RequestParam(defaultValue="0") int page){
        Page<User> users = userService.findAll(page);
        return new ResponseEntity<>(toUserDTO.convert(users.getContent()), HttpStatus.OK);
     
    }
	
	@PreAuthorize("permitAll()")
	@GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id){
        User user = userService.findOneById(id);

        if(user != null) {
            return new ResponseEntity<>(toUserDTO.convert(user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/me")
    public Optional<User> getUserProfile(@RequestHeader (name="Authorization") String token) {
		
    	String username = tokenUtils.getUsernameFromToken(token);
    	
        return userService.findbyUsername(username);

    }
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping(value= "/me",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@RequestHeader (name="Authorization") String token, @Valid @RequestBody UserDTO userDTO){
		
		String username = tokenUtils.getUsernameFromToken(token);
		
		System.out.println("USERNAME");
		System.out.println(username);
		
		if(!username.equals(userDTO.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	
		Optional<User> userToChange = userService.findbyUsername(username);
		
		if(!userToChange.isPresent()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
		
		if(!userToChange.get().getId().equals(userDTO.getId())) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
    	
        User user = toUser.convert(userDTO);

        return new ResponseEntity<>(toUserDTO.convert(userService.updateUser(user)),HttpStatus.OK);
        
    }
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{id}/sightings")
	public ResponseEntity<List<SightingDTO>> getAllSightingsByUser(@RequestHeader (name="Authorization") String token, @PathVariable Long id){
		
		 List<Sighting> sightings = sightingService.findAllSightingsByUserId(id);
		 
	     List<SightingDTO> sightingsDTO = toSightingDTO.convert(sightings);

	     return new ResponseEntity<>(sightingsDTO, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/register")
    public ResponseEntity<UserDTO> create(@RequestHeader (name="Authorization") String token, @RequestBody @Validated UserRegistrationDTO userRegistrationDTO){

        if(userRegistrationDTO.getId() != null || !userRegistrationDTO.getPassword().equals(userRegistrationDTO.getRepeatedPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = toUser.convert(userRegistrationDTO);

        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        user.setPassword(encodedPassword);

        return new ResponseEntity<>(toUserDTO.convert(userService.save(user)), HttpStatus.CREATED);
        
    }
	
    @PreAuthorize("permitAll()")
	@PostMapping(path = "/login")
    public ResponseEntity authenticateUser(@RequestBody AuthUserDTO authUserDTO) {
		System.out.println("DTO");
		System.out.println(authUserDTO);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUserDTO.getUsername(), authUserDTO.getPassword());
        System.out.println("authenticationToken");
        System.out.println(authenticationToken);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println("authentication");
        System.out.println(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authUserDTO.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        
    }
	
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@RequestHeader (name="Authorization") String token, @PathVariable Long id, @Valid @RequestBody UserDTO userDTO){

    	String username = tokenUtils.getUsernameFromToken(token);
		
		if(!username.equals(userDTO.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    	
        if(!id.equals(userDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = toUser.convert(userDTO);

        return new ResponseEntity<>(toUserDTO.convert(userService.save(user)),HttpStatus.OK);
    }
	
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping(value="/{id}", params = "passwordChange")
    public ResponseEntity<Void> changePassword(@RequestHeader (name="Authorization") String token, @PathVariable Long id, @RequestBody UserPasswordChangeDTO userPasswordChangeDTO){
       
    	String username = tokenUtils.getUsernameFromToken(token);
		
		if(!username.equals(userPasswordChangeDTO.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
//		Long userId = tokenUtils.getUserIdFromJwt(token, username);
//
//		if(id != userId) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
    	
        if(!userPasswordChangeDTO.getPassword().equals(userPasswordChangeDTO.getRepeatedPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean result;
        try {
        	result = userService.changePassword(id, userPasswordChangeDTO);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            
        }        
    }	
}
