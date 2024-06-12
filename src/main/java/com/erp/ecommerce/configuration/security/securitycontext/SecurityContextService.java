package com.erp.ecommerce.configuration.security.securitycontext;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.configuration.security.authentication.AccountDetails;
import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.profile.AbstractUserProfile;
import com.erp.ecommerce.repository.user.account.AccountRepository;
import com.erp.ecommerce.repository.user.profile.CustomerRepository;
import com.erp.ecommerce.repository.user.profile.EmployeeRepository;

@Service
public class SecurityContextService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	Function<Account, AbstractUserProfile> strategy;

	public AbstractUserProfile getUserProfile(@AuthenticationPrincipal AccountDetails accountDetails) {
		return strategy.apply(accountRepository.findByUsername(accountDetails.getUsername()).orElseThrow());
	}

}
