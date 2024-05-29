package com.erp.ecommerce.model.user.profile;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.erp.ecommerce.model.user.account.Account;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@SuppressWarnings("unused")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractUserProfile extends AbstractAuditable<Account, Long> {

	/**
	 * Identity Info
	 */
	private String firstName;
	private String lastName;
	private String sex;
	@Column(name = "NationalId", columnDefinition = "char(10)", unique = true)
	private String nationalId;
	private LocalDate birthDate;
	
	/**
	 * Contact Info
	 */
	@Column(length = 10)
	private String mobile;
	@Column(length = 4)
	private String landlineprefix;
	@Column(length = 8)
	private String landline;
	@Column(length = 255)
	private String email;
	@Column(length = 4)
	private byte postalCode;
	@Column(length = 255)
	private String address;
	@Column(length = 255)
	private String footnote;
	
}
