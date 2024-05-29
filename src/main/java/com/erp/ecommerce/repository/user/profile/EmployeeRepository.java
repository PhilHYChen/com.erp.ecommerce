package com.erp.ecommerce.repository.user.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.user.profile.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
