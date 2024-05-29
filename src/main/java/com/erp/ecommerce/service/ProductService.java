package com.erp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.model.product.Product;
import com.erp.ecommerce.repository.product.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;
	
	public List<Product> getAll() {
		return productRepo.findAll();
	}

	public Optional<Product> getById(Integer id) {
		return productRepo.findById(id);
	}
	
	public List<Product> getByCategory(String category) {
		return productRepo.findAllByCategory(category); 
	}
	
}
