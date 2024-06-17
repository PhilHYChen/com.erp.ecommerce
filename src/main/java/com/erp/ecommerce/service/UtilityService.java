package com.erp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.model.utility.PostalCode;
import com.erp.ecommerce.repository.utility.PostalCodeRepository;

@Service
public class UtilityService {

	@Autowired
	private PostalCodeRepository postalCodeRepository;

	public List<PostalCode> getAllPostalCodes() {
		return postalCodeRepository.findAll();
	}

	public Optional<PostalCode> getPostalCodeById(Integer id) {
		return postalCodeRepository.findByPostalCode(id);
	}
	
}
