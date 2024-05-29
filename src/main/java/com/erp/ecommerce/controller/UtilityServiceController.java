package com.erp.ecommerce.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ecommerce.model.utility.PostalCode;
import com.erp.ecommerce.service.UtilityService;

@RestController
@RequestMapping(value = "/api/ex/v1/enum")
public class UtilityServiceController {

	@Autowired
	private UtilityService enumSvc;
	
	@GetMapping(value = "/postal-code")
	public ResponseEntity<List<PostalCode>> getAllPostalCode() {
		return ResponseEntity.ok(enumSvc.getAllPostalCodes());
	}
	
	@GetMapping(value = "/postal-code/{id}")
	public ResponseEntity<PostalCode> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(enumSvc.getPostalCodeById(id));
	}
	
	@PostMapping(value = "/postal-code/add")
	public ResponseEntity<String> add(@RequestBody PostalCode postalCode) {
		Optional<PostalCode> createdPostalCode = enumSvc.createPostalCode(postalCode);
		return (createdPostalCode.isPresent())
				? ResponseEntity.created(URI.create("/api/in/v1/postalCodes/" + createdPostalCode.get().getPostalCode())).build()
				: ResponseEntity.badRequest().body("PostalCode already exists.");
	}
	
	@PutMapping(value = "/postal-code/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody PostalCode postalCode) {
		return (id.equals(postalCode.getPostalCode()) && enumSvc.updatePostalCode(postalCode).isPresent())
				? ResponseEntity.noContent().location(URI.create("/api/in/v1/postalCodes/" + id)).build()
				: ResponseEntity.badRequest().body("PostalCode does not exist.");

	}
	
	@DeleteMapping(value = "/postal-code/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		Optional<PostalCode> deletedPostalCode = enumSvc.deletePostalCode(id);
		return (deletedPostalCode.isEmpty())
				? ResponseEntity.noContent().build()
				: ResponseEntity.badRequest().body("PostalCode does not exist.");
	}
	
}
