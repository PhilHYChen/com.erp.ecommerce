package com.erp.ecommerce.configuration.security;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.account.Authority;
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

		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
		private final String username;
		private String password;
		private final Set<GrantedAuthority> authorities;
		private final Boolean accountNonExpired;
		private final Boolean accountNonLocked;
		private final Boolean credentialsNonExpired;
		private final Boolean enabled;
		private final Long customerId;

		public AccountDetails(Account account) {
			this.username = account.getUsername();
			this.password = account.getPasswordHash();
			this.authorities = account.getAuthorities()
					.stream().map(AccountAuthority::new)
					.collect(Collectors.toUnmodifiableSet());
			this.accountNonExpired = account.getAccountExpiryDate()
					.isAfter(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
			this.accountNonLocked = !account.getAccountLocked();
			this.credentialsNonExpired = account.getCredentialsExpiryDate()
					.isAfter(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
			this.enabled = account.getAccountEnabled();
			this.customerId = account.getCustomer().getId();
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.authorities;
		}

		@Override
		public String getPassword() {
			return this.password;
		}

		@Override
		public String getUsername() {
			return this.username;
		}

		@Override
		public boolean isAccountNonExpired() {
			return this.accountNonExpired;
		}

		@Override
		public boolean isAccountNonLocked() {
			return this.accountNonLocked;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return this.credentialsNonExpired;
		}

		@Override
		public boolean isEnabled() {
			return this.enabled;
		}
		
		public Long getCustomerId() {
			return this.customerId;
		}

	}

	/**
	 * GrantedAuthority Implementation
	 * Serves as an adapter between
	 * ~model.account.Authority and GrantedAuthority.
	 */
	public final class AccountAuthority implements GrantedAuthority {

		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
		
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
