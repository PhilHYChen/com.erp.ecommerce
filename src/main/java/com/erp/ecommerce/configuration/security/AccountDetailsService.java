package com.erp.ecommerce.configuration.security;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.account.Authority;
import com.erp.ecommerce.model.user.profile.AbstractUserProfile;
import com.erp.ecommerce.repository.user.account.AccountRepository;

@Service
public class AccountDetailsService implements UserDetailsService {

	/**
	 * UserDetailsService Implementation
	 */

	@Autowired
	AccountRepository accountRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Account> account;

		if (!username.endsWith("@erp.com.tw")) {
			account = accountRepo.findByUsername(username);
		} else {
			throw new UsernameNotFoundException("");
		}

		return account.map(AccountDetails::new).orElseThrow(() -> new UsernameNotFoundException(""));
	}

	/**
	 * UserDetails Implementation
	 * Serves as an adapter between
	 * ~model.account.Account and UserDetails. The method getProfileId() of this
	 * specific implementation for Ecommerce microservice acquires Customer id.
	 */
	public final class AccountDetails implements UserDetails {

		private Account account;

		public AccountDetails(Account account) {
			this.account = account;
		}

		public Account getAccount() {
			return this.account;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return account.getAuthorities().stream().map(AccountAuthority::new).collect(Collectors.toList());
		}

		@Override
		public String getPassword() {
			return account.getPasswordHash();
		}

		@Override
		public String getUsername() {
			return account.getUsername();
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return !account.getAccountLocked();
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return account.getAccountEnabled();
		}

	}

	/**
	 * GrantedAuthority Implementation
	 * Serves as an adapter between
	 * ~model.account.Authority and GrantedAuthority.
	 */
	public final class AccountAuthority implements GrantedAuthority {

		private Authority authority;

		private AccountAuthority(Authority authority) {
			this.authority = authority;
		};

		@Override
		public String getAuthority() {
			return authority.getName();
		}

	}
}
