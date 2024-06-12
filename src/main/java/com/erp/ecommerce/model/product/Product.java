package com.erp.ecommerce.model.product;

import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.erp.ecommerce.model.order.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String sku;
	private String name;
	private String category;
	private String subCategory;
	private Boolean enabled;
	private Integer price;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private ProductInventory productInventory;
	private String description;
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private Set<OrderItem> orderItems;
}
