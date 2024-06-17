package com.erp.ecommerce.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ecommerce.model.utility.PostalCode;
import com.erp.ecommerce.service.UtilityService;

@RestController
@RequestMapping(value = "/api/ex/v1/enum")
public class UtilityServiceController {

	@Autowired
	private UtilityService utilityService;
	
	@GetMapping(value = "/postal-code")
	public ResponseEntity<List<PostalCode>> getAllPostalCode() {
		return ResponseEntity.ok(utilityService.getAllPostalCodes());
	}
	
	@GetMapping(value = "/postal-code/{id}")
	public ResponseEntity<PostalCode> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(utilityService.getPostalCodeById(id));
	}
	
}
