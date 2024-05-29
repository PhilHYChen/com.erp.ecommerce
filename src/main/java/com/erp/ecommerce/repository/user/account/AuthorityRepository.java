package com.erp.ecommerce.repository.user.account;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.user.account.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
