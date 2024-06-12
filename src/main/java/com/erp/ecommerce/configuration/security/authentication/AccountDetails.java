package com.erp.ecommerce.configuration.security.authentication;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.account.Authority;

/**
 * UserDetails Implementation
 * Serves as an adapter between
 * ~model.account.Account and UserDetails. The method getProfileId() of this
 * specific implementation for this ecommerce microservice acquires Customer id.
 */
public class AccountDetails implements UserDetails {
	
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	private final String username;
	private String password;
	private final Set<GrantedAuthority> authorities;
	private final Boolean accountNonExpired;
	private final Boolean accountNonLocked;
	private final Boolean credentialsNonExpired;
	private final Boolean enabled;

	

	public AccountDetails(Account account) {
		this.username = account.getUsername();
		this.password = account.getPasswordHash();
		this.authorities = account.getAuthorities()
				.stream().map(AuthorityDetails::new)
				.collect(Collectors.toUnmodifiableSet());
		this.accountNonExpired = account.getAccountExpiryDate()
				.isAfter(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
		this.accountNonLocked = !account.getAccountLocked();
		this.credentialsNonExpired = account.getCredentialsExpiryDate()
				.isAfter(OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
		this.enabled = account.getAccountEnabled();
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

	
	/**
	 * GrantedAuthority Implementation
	 * Serves as an adapter between
	 * ~model.account.Authority and GrantedAuthority.
	 */
	
	public final class AuthorityDetails implements GrantedAuthority {

		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
		
		private final String authorityName;

		private AuthorityDetails(Authority authority) {
			this.authorityName = authority.getName();
		};

		@Override
		public String getAuthority() {
			return authorityName;
		}

	}
	
}
