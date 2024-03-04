package com.erp.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.erp.demo.model.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, Integer>{

}
