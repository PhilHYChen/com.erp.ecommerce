package com.erp.ecommerce.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.service.RegistrationService;

@RestController
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;
	
	public ResponseEntity<String> register(@RequestBody Account account) {

		Optional<Account> registeredAccount = registrationService.register(account);
		return (registeredAccount.isPresent()) 
				? ResponseEntity.created(URI.create("/myaccount.html")).build()
				: ResponseEntity.badRequest().header("charset", "utf-8").body("註冊未成功。");
				
	}
}
