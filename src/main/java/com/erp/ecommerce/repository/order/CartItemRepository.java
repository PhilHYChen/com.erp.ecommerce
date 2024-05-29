package com.erp.ecommerce.repository.order;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.order.CartItem;
import com.erp.ecommerce.model.order.Order;
import com.erp.ecommerce.model.user.profile.Customer;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	Collection<Order> findAllByCustomer(Customer loggedInCustomer);

}
