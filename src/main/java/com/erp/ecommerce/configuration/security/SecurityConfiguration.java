package com.erp.ecommerce.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

	/**
	 * Security Filters
	 */

	@Autowired
	AccountDetailsService accountDetailsService;
	@Autowired
	PasswordEncoder passwordEncoder;

//	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
//			throws Exception {
//		return authConfig.getAuthenticationManager();
//	}
//
//	@Bean
//	DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(accountDetailsService);
//		provider.setPasswordEncoder(passwordEncoder);
//		return provider;
//	}
//
//	@Bean
//		SecurityFilterChain ecommerce
//		


	/**
	 * Password Encoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
