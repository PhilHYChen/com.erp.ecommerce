package com.erp.ecommerce.model.user.account;

import java.util.Set;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Authority extends AbstractAuditable<Account, Long> {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authorities")
	private Set<Account> accounts;

}
