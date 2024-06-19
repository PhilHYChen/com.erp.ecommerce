package com.erp.ecommerce.configuration.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.erp.ecommerce.configuration.security.userdetails.AccountDetails;
import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.repository.user.account.AccountRepository;

@Component("accountAuditorAware")
public class AccountAuditorAware implements AuditorAware<Account> {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Optional<Account> getCurrentAuditor() {

		String username =  Optional.ofNullable(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getPrincipal)
				.map(AccountDetails.class::cast)
				.map(AccountDetails::getUsername)
				.orElseThrow();
		
		return accountRepository.findByUsername(username);
	}

}
