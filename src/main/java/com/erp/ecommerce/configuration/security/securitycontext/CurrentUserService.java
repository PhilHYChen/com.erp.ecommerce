package com.erp.ecommerce.configuration.security.securitycontext;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.configuration.security.userdetails.AccountDetails;
import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.profile.AbstractUserProfile;
import com.erp.ecommerce.repository.user.account.AccountRepository;

@Service
public class CurrentUserService<T extends AbstractUserProfile> {

	@Autowired
	AccountRepository accountRepository;

	private final Function<Account, T> strategy;

	public CurrentUserService(Function<Account, T> strategy) {
		this.strategy = strategy;
	};
	
	public Account getCurrentUserAccount(@AuthenticationPrincipal AccountDetails accountDetails) {
		return accountRepository.findByUsername(accountDetails.getUsername()).orElseThrow();
	}

	public T getCurrentUserProfile(@AuthenticationPrincipal AccountDetails accountDetails) {
		return strategy.apply(accountRepository.findByUsername(accountDetails.getUsername()).orElseThrow());
	}

}
