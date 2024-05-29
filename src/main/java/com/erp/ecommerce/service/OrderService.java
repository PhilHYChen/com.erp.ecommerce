package com.erp.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.configuration.security.SecurityContextService;
import com.erp.ecommerce.model.order.Order;
import com.erp.ecommerce.model.order.OrderItem;
import com.erp.ecommerce.repository.order.CartItemRepository;
import com.erp.ecommerce.repository.order.DispatchmentRepository;
import com.erp.ecommerce.repository.order.OrderItemRepository;
import com.erp.ecommerce.repository.order.OrderRepository;
import com.erp.ecommerce.repository.product.ProductRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	DispatchmentRepository dispatchmentRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	SecurityContextService securityContextService;

	public List<Order> getByCustomer() {
		List<Order> orderList = orderRepository.findAllByCustomer(securityContextService.getLoggedInCustomer());
		return orderList;
	}

	
	/**
	 * JSON 寫法：
	 * {
        "recipient": "許怡如",
        "cellphone": "0984849465",
        "landlineprefix": "02",
        "landline": "12345678",
        "email": "user1@example.com",
        "postalCode": 100,
        "address": "台北市信義區忠孝東路一段43號",
        "footnote": null,
        "orderItems": [
            {
                "id": 1,
                "oid": 1,
                "pid": 1,
                "price": 1000,
                "quantity": 1
            }
        ],
	 * 
	 * }
	 * @param order
	 * @return
	 */
	
	public Optional<Order> create(Order order) {
		
		order.setCustomer(securityContextService.getLoggedInCustomer());
		validateOrderItems(order);
		
		if (order.getOrderItems().isEmpty()) {
			return Optional.empty();
		} 
		
		validateDispatchment(order);
		validateTotal(order);
		
		if (order.getDispatchments().isEmpty()) {
			order.setDid(dispatchmentRepository.save(order.getDispatchment()).getDid());
		}
		
		order = orderRepository.save(order);
		Integer oid = order.getOid();
		order.getOrderItems().forEach(orderItem -> orderItem.setId(oid));
		orderItemRepository.saveAll(order.getOrderItems());
		
		// 刪除購物車內已訂購商品。
		order.getOrderItems().forEach(orderItem 
				-> cartItemRepository.deleteById(
						cartItemRepository.findAllByCustomer(securityContextService.getLoggedInCustomer()).stream().filter(
								cartItem -> cartItem.getPid().equals(orderItem.getPid())).
						findFirst().get().getId()));
		

		
		return Optional.ofNullable(order); 
		 
	}
	
	
	/**
	 * Validation
	 * @param orderItems
	 */
	private void validateOrderItems(Order requestedOrder) {
		Set<OrderItem> requestedOrderItems = requestedOrder.getOrderItems();
		Set<OrderItem> validatedOrderItems = requestedOrderItems.stream()
				.filter(item -> item.getQuantity() > 0) // 移除數量為零的項目。
				.forEach(item -> item.setPrice(item.getProduct().getPrice()))
				.
				
		
		
		
		// 驗證當前 Product 價格：
		requestedOrderItems.stream()
			.forEach(requestedOrderItem -> requestedOrderItem.setPrice(requestedOrderItem.getProduct().getPrice()))
			.map(requestedOrderItem -> );
		// 驗證當前 Product 庫存量： 
		requestedOrderItems.forEach(
				orderItem -> orderItem.setQuantity(
						Math.min(orderItem.getQuantity(), productRepository.findById(orderItem.getPid()).get().getInventory())));
		// 移除數量為 0 的 OrderItem：
		validatedOrderItems = requestedOrderItems.stream().filter(
				orderItem -> orderItem.getQuantity() > 0).collect(Collectors.toList());
		// 更新 Order 的 OrderItem 列表：
		orderDetail.setOrderItems(validatedOrderItems);
	}
	
	private void validateDispatchment(Order order) {
		Boolean requiresDispatchment = false;
		List<OrderItem> orderItems = order.getOrderItems();		
		requiresDispatchment = orderItems.stream().anyMatch(
				orderItem -> productRepository.findById(orderItem.getPid()).get().getShippingMethod().equals("派送"));
		if (!requiresDispatchment) {
			order.setDispatchments(null);
		}
	}
	
	private void validateTotal(Order order) {
		order.setTotal(0);
		order.getOrderItems().forEach(orderItem -> order.setTotal(order.getTotal() + orderItem.getPrice() * orderItem.getQuantity()));
	}
	
}
