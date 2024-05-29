package com.erp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.configuration.security.SecurityConfiguration.AccountDetails;
import com.erp.ecommerce.model.order.CartItem;
import com.erp.ecommerce.repository.CartItemRepo;
import com.erp.ecommerce.repository.ProductRepo;

@Service
public class CartSvc {

	@Autowired
	CartItemRepository cartItemRepo;
	@Autowired
	ProductRepository productRepo;
	
	/**
	 * CRUD Operation
	 */
	
	public List<CartItem> getAll() {
		AccountDetails loggedInMember = getLoggedInMember();
		return cartItemRepo.findAllByMid(loggedInMember.getProfileId());
	}
	
	public Optional<CartItem> getById(Integer id) {
		AccountDetails loggedInMember = getLoggedInMember();
		Optional<CartItem> cartItem = cartItemRepo.findById(id);
		return (cartItem.isPresent() 
				&& cartItem.get().getMid() == loggedInMember.getProfileId())
				? cartItem
				: Optional.empty();
	}			

	public Optional<CartItem> add(Integer pid, Integer quantity) {
		AccountDetails loggedInMember = getLoggedInMember();		
		List<CartItem> cart = cartItemRepo.findAllByMid(loggedInMember.getProfileId());
		CartItem addedCartItem;
		
		/**
		 * 輪詢若該會員的購物車：
		 * 若其內已有同款商品的 CartItem，則...
		 * 1. 直接在該筆 CartItem 紀錄上進行更新、
		 * 2. 該筆 CartItem 待購數量 + 1 至該款商品庫存為限、
		 * 3. 回傳該筆 CartItem 給 Controller。
		 */
		for (CartItem cartItem : cart) {
			if (cartItem.getPid() == pid) {
				addedCartItem = cartItem;				
				cartItem.setQuantity(validateQuantity((cartItem.getQuantity() + 1), pid));
				return Optional.ofNullable(addedCartItem);
			}
		}
		
		/**
		 * 若該會員的購物車內，查無同款商品的 CartItem，則...
		 * 確認該會員購物車內商品種類是否已達上限（10），若否，則...
		 * 1. 創建一筆新的 CartItem 紀錄、
		 * 2. 回傳該筆 CartItem 給 Controller。
		 */
		if (cart.size() <= 10) {
			cartItemRepo.save(addedCartItem = new CartItem(-1, loggedInMember.getProfileId(), pid, validateQuantity(1, pid)));
			return Optional.ofNullable(addedCartItem);	
		} else {
			return Optional.empty();
		}
		
	}
	
	public Optional<CartItem> update(CartItem cartItemToUpdate) {
		AccountDetails loggedInMember = getLoggedInMember();
		return (cartItemToUpdate.getMid() == loggedInMember.getProfileId())
				? Optional.of(cartItemRepo.save(cartItemToUpdate))
				: Optional.empty();
	}
	
	public List<CartItem> deleteAll() {
		AccountDetails loggedInMember = getLoggedInMember();
		cartItemRepo.deleteAllByMid(loggedInMember.getProfileId());
		return cartItemRepo.findAllByMid(loggedInMember.getProfileId());
	}
	

	public Optional<CartItem> deleteById(Integer id) {
		AccountDetails loggedInMember = getLoggedInMember();
		Optional<CartItem> cartItem = cartItemRepo.findById(id);
		if (cartItem.isPresent() 
			&& cartItem.get().getMid() == loggedInMember.getProfileId()) {
			cartItemRepo.deleteById(id);
		}
		return cartItemRepo.findById(id);
	}

	/**
	 * Validation
	 */

	private Integer validateQuantity(Integer quantity, Integer pid) {
		return Math.min(quantity, productRepo.findById(pid).get().getInventory());
	}
	
	/**
	 * Authentication
	 */
	private AccountDetails getLoggedInMember() {
		return (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
