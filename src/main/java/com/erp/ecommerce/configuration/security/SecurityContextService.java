package com.erp.ecommerce.configuration.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.configuration.security.AccountDetailsService.AccountDetails;
import com.erp.ecommerce.model.user.profile.Customer;

@Service
public class SecurityContextService {

	public Customer getLoggedInCustomer() {
		AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		return accountDetails.getAccount().getCustomer();
	}
	
}
