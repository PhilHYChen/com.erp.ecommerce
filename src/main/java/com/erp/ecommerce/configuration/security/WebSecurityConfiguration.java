package com.erp.ecommerce.configuration.security;

import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.erp.ecommerce.configuration.security.csrfprotection.CsrfCookieFilter;
import com.erp.ecommerce.configuration.security.csrfprotection.SpaCsrfTokenRequestHandler;
import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.profile.Customer;

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
	        .csrf(csrf -> csrf
	            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	            .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
        		)
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers(HttpMethod.GET, "/api/ex/v1/products", "/api/ex/v1/products/**").permitAll()
	            .requestMatchers(HttpMethod.GET, "/api/ex/v1/enum", "/api/ex/v1/enum/**").permitAll()
	            .requestMatchers("/api/ex/v1/**").authenticated()
	            .anyRequest().permitAll()
	            )
	        .formLogin(form -> form
	            .loginPage("/javaProject/singIn/index.html")
	            .loginProcessingUrl("/login")
	            .defaultSuccessUrl("/index.html")
	            .failureForwardUrl("/javaProject/singIn/index.html?error")
	            .permitAll()
        		)
	        .logout(logout -> logout
        		.logoutUrl("/logout")
        		.logoutSuccessUrl("/javaProject/singIn/index.html")
        		.invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        		)
			.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
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
	 * com.erp.ecommerce.configuration.security.securitycontext.CurrentUserService
	 * to determine which profile acquisition strategy implementation
	 * is suitable. 
	 */
	@Bean
	Function<Account, Customer> configureCurrentUserServiceStrategy() {
		return (account) -> account.getCustomer();
	};
	
}
