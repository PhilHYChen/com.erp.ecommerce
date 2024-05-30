package com.erp.ecommerce.model.order;

import java.util.Set;

import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.profile.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Order extends AbstractAuditable<Account, Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Customer customer;
	private String status;
	private Integer amount;
	private Integer total;
	private String shippingMethod;
	@Column(length = 45)
	private String recipient;
	@Column(length = 10)
	private String cellphone;
	@Column(length = 4)
	private String landlineprefix;
	@Column(length = 8)
	private String landline;
	@Column(length = 255)
	private String email;
	private Integer postalCode;
	private String address;
	@OneToMany(mappedBy = "order")
	private Set<OrderItem> orderItems;
	@OneToMany(mappedBy = "order")
	private Set<Dispatchment> dispatchments;
	private String footnote;

}
