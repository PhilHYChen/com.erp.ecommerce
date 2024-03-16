package com.erp.demo.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.PostalCode;
import com.erp.demo.repo.PostalCodeRepo;

@Service
public class EnumMgmt {
	
	@Autowired
	private PostalCodeRepo postalCodeRepo;

	public List<PostalCode> getAllPostalCodes() {
		return postalCodeRepo.findAll();
	}	

}
