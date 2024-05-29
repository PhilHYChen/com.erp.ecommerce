package com.erp.ecommerce.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.order.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
