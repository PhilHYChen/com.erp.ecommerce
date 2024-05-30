package com.erp.ecommerce.model.user.account;

import java.time.OffsetDateTime;
import java.util.Set;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.erp.ecommerce.model.user.profile.Customer;
import com.erp.ecommerce.model.user.profile.Employee;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Account extends AbstractAuditable<Account, Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Credentials
	 */
	@Column(unique = true)
	@NotNull
	private String username;
	@NotNull
	private String passwordHash;
	//

	/**
	 * Type and related profile (either Customer or Employee) 
	 * Before persisting, validation should ensure that: 
	 * 1. Field accountType is not null. (@NotNull)
	 * 2. Field accountType is not updatable. (@Column(updatable = false)) 
	 * 3. Either customer or employee is not null. (Additional logic required.) 
	 * 4. Class of whichever mentioned field (customer or employee) is not null 
	 * should correspond to the Enum/String value of accountType. 
	 * 5. Only accounts with "Admin" AccountAuthority is permitted 
	 * to create user account of "Employee" AccountType. (Per JPA Auditing)
	 */	
	@Column(updatable = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	@Nullable
	@OneToOne
	@JoinColumn
	private Customer customer;
	@Nullable
	@OneToOne
	@JoinColumn
	private Employee employee;

	/**
	 * Authorities and additional Access Control required by 
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "accounts_authorities", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authorities;
	@Column(name = "account_enabled")
	private Boolean accountEnabled;
	@Column(name = "account_locked")
	private Boolean accountLocked;
	@Column(name = "account_expiry_date")
	private OffsetDateTime accountExpiryDate;
	@Column(name = "credentials_expiry_date")
	private OffsetDateTime credentialsExpiryDate;

	/**
	 * Utility Enumeration
	 */
	private enum AccountType {
		CUSTOMER, EMPLOYEE
	}

}
