package com.erp.ecommerce.model.order;

import java.time.OffsetDateTime;
import java.util.Set;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.profile.Employee;

import jakarta.annotation.Nullable;
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
public class Dispatchment extends AbstractAuditable<Account, Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn
	private Order order;
	@ManyToMany
	@JoinTable(
			name = "dispatchment_employee", 
			joinColumns = @JoinColumn(name = "dispatchment_id"), 
			inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private Set<Employee> employees;
	private String status;
	@NotNull
	private OffsetDateTime scheduledAt;
	@Nullable
	private OffsetDateTime accomplishedAt;

}
