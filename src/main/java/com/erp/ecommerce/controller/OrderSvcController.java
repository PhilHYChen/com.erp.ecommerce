package com.erp.ecommerce.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ecommerce.model.order.Order;
import com.erp.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/ex/v1/orders")
public class OrderSvcController {

	
	@Autowired
	OrderService orderSvc;
	
	@GetMapping()
	public ResponseEntity<List<Order>> getByMid() {
		return ResponseEntity.ok().body(orderSvc.getByCustomer());
	}
	
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> add(@RequestBody Order order) {
		Optional<Order> createdOrder = orderSvc.create(order);
		return (createdOrder.isPresent())
				? ResponseEntity.created(URI.create("/api/in/v1/orders/" + createdOrder.get().getOid())).build()
				: ResponseEntity.badRequest().body("Order already exists.");
	}

}
