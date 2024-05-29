package com.erp.ecommerce.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.product.ProductInventory;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long>{

}
