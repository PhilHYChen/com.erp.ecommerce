package com.erp.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ecommerce.model.product.Product;
import com.erp.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/ex/v1/products")
public class ProductServiceController {
	
	@Autowired
	ProductService productSvc;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok().body(productSvc.getAll());
	}	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> getById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(productSvc.getById(id));
	}
	
	@GetMapping(value = "/categories/{category}")
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("category") String category) {
		return ResponseEntity.ok().body(productSvc.getByCategory(category));
	}
	
}
