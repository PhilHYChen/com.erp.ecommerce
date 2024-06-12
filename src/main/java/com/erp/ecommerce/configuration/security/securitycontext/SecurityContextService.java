package com.erp.ecommerce.configuration.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.configuration.security.AccountDetailsService.AccountDetails;
import com.erp.ecommerce.model.user.profile.Customer;
import com.erp.ecommerce.repository.user.profile.CustomerRepository;

@Service
public class SecurityContextService {

	@Autowired
	CustomerRepository customerRepository;
	
	public Optional<Customer> getLoggedInCustomer() {
		AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		return customerRepository.findById((accountDetails.getCustomerId()));
	}
	
}
