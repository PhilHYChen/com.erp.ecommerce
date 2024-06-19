package com.erp.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.repository.user.account.AccountRepository;
import com.erp.ecommerce.repository.user.profile.CustomerRepository;

import jakarta.validation.Valid;

@Service
public class RegistrationService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	public Optional<Account> register(@Valid Account account) {
		return (isValidAccount(account))
				? Optional.of(accountRepository.save(account))
				: Optional.empty();
	}

	private boolean isValidAccount(Account account) {
		
		return Optional.of(account)
				/**
				 * Apply all relevant validation logics...
				 */
				.filter(this::isUniqueUsername)
				.filter(this::isUniqueEmail)
				.filter(this::isUniqueNationalId)
				.isPresent();
	}
	
	private boolean isUniqueUsername(Account account) {
		return !accountRepository.existsByUsername(account.getUsername());
	}
	
	private boolean isUniqueEmail(Account account) {
		return !customerRepository.existsByEmail(account.getCustomer().getEmail());
	}
	
	private boolean isUniqueNationalId(Account account) {
		return !customerRepository.existsByNationalId(account.getCustomer().getNationalId());
	}
}
