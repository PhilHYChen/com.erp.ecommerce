package com.erp.ecommerce.configuration.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.profile.AbstractUserProfile;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfiguration {

	/**
	 * Security Filters
	 * @throws Exception 
	 */

	@Bean
		SecurityFilterChain securityFilterChainProvider(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeHttpRequests(authorize -> authorize
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
					.loginPage("/login.html")
					.loginProcessingUrl("/login")
					.defaultSuccessUrl("/index.html")
					.failureForwardUrl("/login.html?error")
					.permitAll())
			.httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}
		
	

	/**
	 * Password Encoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * This Bean is used solely by the custom class:
	 * com.erp.ecommerce.configuration.security.SecurityContextService
	 * to determine which profile acquisition strategy implementation
	 * is suitable. 
	 */
	@Bean
	Function<Account, AbstractUserProfile> configureSecurityContextServiceStrategy() {
		return (account) -> account.getCustomer();
	};
	
	
}
