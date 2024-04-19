package com.ftninformatika.jwd.modul3.flowrSpot.web.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.AuthKorisnikDTO;
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
	
	@GetMapping("/{id}/sightings")
	public ResponseEntity<List<SightingDTO>> getAllSightingsByUser(@PathVariable Long id){
		
		 List<Sighting> sightings = sightingService.findAllSightingsByUsers(id);
		 
	     List<SightingDTO> sightingsDTO = toSightingDTO.convert(sightings);

	     return new ResponseEntity<>(sightingsDTO, HttpStatus.OK);
		
	    }
	
	@PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Validated UserRegistrationDTO userRegistrationDTO){

        if(userRegistrationDTO.getId() != null || !userRegistrationDTO.getPassword().equals(userRegistrationDTO.getRepeatedPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = toUser.convert(userRegistrationDTO);

        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        user.setPassword(encodedPassword);

        return new ResponseEntity<>(toUserDTO.convert(userService.save(user)), HttpStatus.CREATED);
    }
	
	@PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO){

        if(!id.equals(userDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = toUser.convert(userDTO);

        return new ResponseEntity<>(toUserDTO.convert(userService.save(user)),HttpStatus.OK);
    }
	
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
	
	@GetMapping
    public ResponseEntity<List<UserDTO>> get(@RequestParam(defaultValue="0") int page){
        Page<User> users = userService.findAll(page);
        return new ResponseEntity<>(toUserDTO.convert(users.getContent()), HttpStatus.OK);
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, params = "promenaLozinke")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody UserPasswordChangeDTO userPasswordChangeDTO){
       
        if(!userPasswordChangeDTO.getPassword().equals(userPasswordChangeDTO.getRepeatedPassword())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean rezultat;
        try {
            rezultat = userService.changePassword(id, userPasswordChangeDTO);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(rezultat) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
	
	@PostMapping(path = "/auth")
    public ResponseEntity authenticateUser(@RequestBody AuthKorisnikDTO authKorisnikDTO) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authKorisnikDTO.getUsername(), authKorisnikDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(authKorisnikDTO.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
	
}
