package com.erp.ecommerce.repository.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.order.Order;
import com.erp.ecommerce.model.user.profile.Customer;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByCustomer(Customer loggedInCustomer);

}
