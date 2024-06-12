package com.erp.ecommerce;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.profile.Customer;
import com.erp.ecommerce.repository.user.account.AccountRepository;
import com.erp.ecommerce.repository.user.account.AuthorityRepository;
import com.erp.ecommerce.repository.user.profile.CustomerRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
		
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	AuthorityRepository authorityRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
//	@Override
//    public void run(String... args) {
//		accountRepo.save(
//				new Account(
//						null, 
//						"customer",
//						passwordEncoder.encode("password"),
//						Account.AccountType.CUSTOMER, 
//						customerRepo.save(new Customer()), null, null, true, false,
//						OffsetDateTime.of(2999, 12, 31, 23, 59, 59, 0, ZoneOffset.ofHours(8)), 
//						OffsetDateTime.of(2999, 12, 31, 23, 59, 59, 0, ZoneOffset.ofHours(8))));
//		System.out.println("Account saved");
//	}

}
