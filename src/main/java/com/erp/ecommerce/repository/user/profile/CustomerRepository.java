package com.erp.ecommerce.repository.user.profile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.user.profile.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findById(Long id);
}
