package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.ObjectModel;
import net.javaguides.springboot.repository.ObjectRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class ObjectController {

	@Autowired
	private ObjectRepository objectRepository;

	// get all objects
	@GetMapping("/objects")
	public List<ObjectModel> getAllObjects() {
		return objectRepository.findAll();
	}

	// create object rest api
	@PostMapping("/objects")
	public ObjectModel createObject(@RequestBody ObjectModel object) {
		return objectRepository.save(object);
	}

	// get object by id rest api
	@GetMapping("/objects/{id}")
	public ResponseEntity<ObjectModel> getObjectById(@PathVariable Long id) {
		ObjectModel object = objectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Object not exist with id :" + id));
		return ResponseEntity.ok(object);
	}

	// update object rest api

	@PutMapping("/objects/{id}")
	public ResponseEntity<ObjectModel> updateObject(@PathVariable Long id, @RequestBody ObjectModel objectDetails) {
		ObjectModel object = objectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Object not exist with id :" + id));

		object.setName(objectDetails.getName());
		object.setDescription(objectDetails.getDescription());
		object.setImage(objectDetails.getImage());		

		ObjectModel updatedObject = objectRepository.save(object);
		return ResponseEntity.ok(updatedObject);
	}

	// delete object rest api
	@DeleteMapping("/objects/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteObject(@PathVariable Long id) {
		ObjectModel object = objectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Object not exist with id :" + id));

		objectRepository.delete(object);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
