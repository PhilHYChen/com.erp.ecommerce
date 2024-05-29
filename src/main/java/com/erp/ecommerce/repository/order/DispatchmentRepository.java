package com.erp.ecommerce.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.order.Dispatchment;

public interface DispatchmentRepository extends JpaRepository<Dispatchment, Long>{

}
