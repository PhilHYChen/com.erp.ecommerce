package com.erp.demo.model.physical;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Generated 2024�~3��2�� �U��7:02:46 by Hibernate Tools 6.3.1.Final

/**
 * PaymentMethod generated by hbm2java
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentMethod implements java.io.Serializable {
	@Id
	@Column(length=50)
	private String paymentCode;
	@Column(length=255)
	private String paymentMethod;
	@Column(length=10)
	private String providerCode;
	@Column(length=255)
	private String providerName;
	private char enabled;

}
