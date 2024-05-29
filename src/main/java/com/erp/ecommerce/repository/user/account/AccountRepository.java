package com.erp.ecommerce.repository.user.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.user.account.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByUsername(String username);

}
