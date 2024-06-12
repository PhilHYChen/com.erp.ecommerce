package com.erp.ecommerce.model.user.profile;

import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.erp.ecommerce.model.order.Dispatchment;
import com.erp.ecommerce.model.organization.Department;
import com.erp.ecommerce.model.user.account.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Employee extends AbstractUserProfile {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Account Info
	 */
	@OneToOne(mappedBy = "employee")
	private Account account;
	
	/**
	 * Employment Info
	 */
	@ManyToOne
	@JoinColumn
	private Department department;
	private String title;
	private Integer salary;
	
	/**
	 * Dispatchment Info
	 */
	@ManyToMany(mappedBy = "employees")
	private Set<Dispatchment> dispatchments;
}
