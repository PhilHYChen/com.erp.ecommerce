package com.erp.ecommerce.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.organization.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
