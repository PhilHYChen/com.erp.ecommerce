package com.erp.ecommerce.repository.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findById(Integer id);

	List<Product> findAllByCategory(String category);

}
