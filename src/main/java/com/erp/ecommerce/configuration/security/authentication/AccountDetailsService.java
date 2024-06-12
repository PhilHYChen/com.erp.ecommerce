package com.erp.ecommerce.configuration.security.authentication;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.repository.user.account.AccountRepository;

@Service
public class AccountDetailsService implements UserDetailsService {

	@Autowired
	AccountRepository accountRepo;

	/**
	 * UserDetailsService Implementation
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Account> account;

		/**
		 * This ecommerce microservice only authenticates non-employee accounts,
		 * that is, accounts of which usernames do not end with "@erp.com.tw".
		 */
		if (!username.endsWith("@erp.com.tw")) {
			account = accountRepo.findByUsername(username);
		} else {
			throw new UsernameNotFoundException("Employee account is not permitted.");
		}

		return account.map(AccountDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("Customer account username not found."));
	}

}
