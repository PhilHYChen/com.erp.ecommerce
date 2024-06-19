package com.erp.ecommerce.configuration.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.erp.ecommerce.configuration.security.userdetails.AccountDetails;
import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.repository.user.account.AccountRepository;

@Component("auditorAware")
public class AccountAuditorAware implements AuditorAware<Account> {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Optional<Account> getCurrentAuditor() {
		AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Account> account = accountRepository.findByUsername(accountDetails.getUsername());
		return account;
	}

}
