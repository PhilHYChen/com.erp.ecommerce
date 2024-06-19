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

import com.erp.ecommerce.configuration.security.securitycontext.annotation.CurrentUserProfile;
import com.erp.ecommerce.model.user.profile.Customer;
import com.erp.ecommerce.service.UserProfileService;

@RestController
@RequestMapping("/api/ex/v1/members")
public class CurrentCustomerProfileServiceController {
	
	@Autowired
	UserProfileService userProfileService;
	
	/**
	 * CRUD Operation
	 */
	
	@SuppressWarnings("unchecked")
	@GetMapping
	public ResponseEntity<Customer> getCustomerProfile(@CurrentUserProfile Customer customer) {
		return ResponseEntity.of(userProfileService.getUserProfile(customer));
	}
	
//	@PostMapping
//	public ResponseEntity<String> register(@RequestBody Customer member) {
//		Optional<Customer> createdMember = userProfileService.register(member);
//		return (createdMember.isPresent())
//				? ResponseEntity.created(URI.create("/api/ex/v1/members" + createdMember.get().getMid())).build()
//				: ResponseEntity.badRequest().body("Member already exists.");
//	}
//	
//	@PutMapping
//	public ResponseEntity<String> updateSelf(@RequestBody Customer member) {
//		return (userProfileService.updateSelf(member).isPresent())
//				? ResponseEntity.noContent().location(URI.create("/api/ex/v1/members")).build()
//				: ResponseEntity.badRequest().body("Member does not exist.");
//
//	}
//	
//	@DeleteMapping
//	public ResponseEntity<String> deleteSelf() {
//		Optional<Customer> deletedMember = userProfileService.deleteSelf();
//		return (deletedMember.isEmpty())
//				? ResponseEntity.noContent().build()
//				: ResponseEntity.badRequest().body("Member does not exist.");
//	}
//	
//	
//	/**
//	 * Form Pre-submission Validation
//	 */
//	
//	@PostMapping(value = "/validate/username")
//	public ResponseEntity<Boolean> isValidUsername(@RequestBody Customer member) {
//		return ResponseEntity.ok(userProfileService.isValidUsername(member));
//	}
//
//	@PostMapping(value = "/validate/national-id")
//	public ResponseEntity<Boolean> isValidNationalId(@RequestBody Customer member) {
//		return ResponseEntity.ok(userProfileService.isValidNationalId(member));
//	}
	
}
