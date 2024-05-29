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
		return postalCodeRepository.findById(id);
	}

	public Optional<PostalCode> createPostalCode(PostalCode postalCode) {
		return (!postalCodeRepository.existsById(postalCode.getPostalCode()))
				? Optional.of(postalCodeRepository.save(postalCode))
				: Optional.empty();
	}

	public Optional<PostalCode> updatePostalCode(PostalCode postalCode) {
		return Optional.of(postalCodeRepository.save(postalCode));
	}
	
	public Optional<PostalCode> deletePostalCode(Integer id) {
		postalCodeRepository.deleteById(id);
		return postalCodeRepository.findById(id);		
	}
	
}
