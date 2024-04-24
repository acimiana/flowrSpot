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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.model.Sighting;
import com.ftninformatika.jwd.modul3.flowrSpot.security.TokenUtils;
import com.ftninformatika.jwd.modul3.flowrSpot.service.FlowerService;
import com.ftninformatika.jwd.modul3.flowrSpot.service.SightingService;
import com.ftninformatika.jwd.modul3.flowrSpot.support.FlowerDTOToFlower;
import com.ftninformatika.jwd.modul3.flowrSpot.support.FlowerToFlowerDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.support.SightingToSightingDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.FlowerDTO;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.SightingDTO;

@RestController
@RequestMapping(value = "/api/flowers", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlowerController {
	
	@Autowired
	private FlowerService flowerService;
	
	@Autowired
	private SightingService sightingService;
	
	@Autowired
	private FlowerToFlowerDTO toFlowerDTO;
	
	@Autowired
	private FlowerDTOToFlower toFlower;
	
	@Autowired
	private SightingToSightingDTO toSightingDTO;
	
	@PreAuthorize("permitAll()")
	@GetMapping
    public ResponseEntity<List<FlowerDTO>> getAll(){

        List<Flower> flowers = flowerService.getAll();
        List<FlowerDTO> flowersDTO = toFlowerDTO.convert(flowers);

        return new ResponseEntity<>(flowersDTO, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<FlowerDTO> get(@PathVariable Long id){
		
    	Flower flower = flowerService.findOneById(id);
	        
	    if (flower == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	                
	    FlowerDTO flowerDTO = toFlowerDTO.convert(flower);
	        
	    return new ResponseEntity<>(flowerDTO, HttpStatus.OK);
    }
	
    @PreAuthorize("permitAll()")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlowerDTO> create(@Valid @RequestBody FlowerDTO flowerDTO){
		Flower flower = toFlower.convert(flowerDTO);
		Flower savedFlower= flowerService.save(flower);
        
		FlowerDTO savedFlowerDTO = toFlowerDTO.convert(savedFlower);

        return new ResponseEntity<>(savedFlowerDTO, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FlowerDTO> update(@PathVariable Long id, @Valid @RequestBody FlowerDTO flowerDTO){

        if(!id.equals(flowerDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Flower s = flowerService.findOneById(flowerDTO.getId());
        
        if (s == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Flower flower = toFlower.convert(flowerDTO);
        Flower savedFlower = flowerService.edit(flower);
        FlowerDTO flowerUpdated = toFlowerDTO.convert(savedFlower);

        return new ResponseEntity<>(flowerUpdated, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteLike(@PathVariable Long id){
	     boolean deleted = flowerService.delete(id);

	     if(deleted == true) {
	         return new ResponseEntity<>(HttpStatus.OK);
	     } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
    
    @GetMapping("/{id}/sightings")
    public ResponseEntity<List<SightingDTO>> getAllByFlower(@PathVariable Long id){
    	
        List<Sighting> sightings = sightingService.findAllSightingsByFlowerId(id);
        List<SightingDTO> sightingsDTO = toSightingDTO.convert(sightings);

        return new ResponseEntity<>(sightingsDTO, HttpStatus.OK);
    }
    
    @GetMapping("/favorites")
    public ResponseEntity<List<FlowerDTO>> findAllFavoriteFlowersByUserId(@PathVariable String token, String secretKey){
    	
    	Long myId = TokenUtils.getUserIdFromJwt(token, secretKey);
    	
        List<Flower> flowers = flowerService.findAllFavoriteFlowersByUserId(myId);
		List<FlowerDTO> flowersDTO = toFlowerDTO.convert(flowers);

        return new ResponseEntity<>(flowersDTO, HttpStatus.OK);
    }
    
}
