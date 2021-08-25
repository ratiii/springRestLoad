package com.springrest.springrest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.dao.LoadDao;
import com.springrest.springrest.entities.Loads;
import com.springrest.springrest.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/api/v1")
public class MyController {
	

	
	@Autowired
	private LoadDao loadDao;
	
	
	//get all load details
	
	@GetMapping("loads")
	public List<Loads> getAllLoads(){
		
		return this.loadDao.findAll();
				
	}
	
	//getting load by load id
	@GetMapping("/loads/{id}")
	public ResponseEntity<Loads> getLoadById(@PathVariable(value = "id") Long loadId) throws ResourceNotFoundException {
		Loads loads = loadDao.findById(loadId).orElseThrow(() -> new ResourceNotFoundException("load not found for this id :: " + loadId));
		return ResponseEntity.ok().body(loads);
	}
	
	//adding loads
	@PostMapping("details")
	public Loads addLoad(@RequestBody Loads load) {
		return this.loadDao.save(load);
	}
	
	
	//updating load details
	@PutMapping("loads/{id}")
	public ResponseEntity<Loads> updateLoad(@PathVariable(value= "id") Long loadId,
			@Validated @RequestBody Loads loadDetails) throws ResourceNotFoundException{
		
		Loads loads = loadDao.findById(loadId).orElseThrow(() -> new ResourceNotFoundException("load not found for this id :: " + loadId));
		
		loads.setUnloadingPoint(loadDetails.getUnloadingPoint());
		loads.setLoadingPoint(loadDetails.getLoadingPoint());
		loads.setProductType(loadDetails.getProductType());
		loads.setTruckType(loadDetails.getTruckType());
		loads.setNoOfTrucks(loadDetails.getNoOfTrucks());
		loads.setWeight(loadDetails.getWeight());
		loads.setDate(loadDetails.getDate());
		
		return ResponseEntity.ok(this.loadDao.save(loads));

		
	}
	
	//deleting a load
	@DeleteMapping("loads/{id}")
	public Map<String, Boolean> deleteLoad(@PathVariable(value = "id") Long loadId) throws ResourceNotFoundException{
		Loads loads = loadDao.findById(loadId).orElseThrow(() -> new ResourceNotFoundException("load not found for this id :: " + loadId));
		
		this.loadDao.delete(loads);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
			
		}
		
	

}
