package com.erp.ecommerce.model.user.profile;

import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.erp.ecommerce.model.order.CartItem;
import com.erp.ecommerce.model.order.Order;
import com.erp.ecommerce.model.user.account.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Customer extends AbstractUserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Account Info
	 */
	@OneToOne(mappedBy = "customer")
	private Account account;

	/**
	 * Cart Info
	 */
	@OneToMany(mappedBy = "customer")
	private Set<CartItem> cartItem;
	@OneToMany(mappedBy = "order")
	private Set<Order> order;

}
