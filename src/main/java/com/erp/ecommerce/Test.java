package com.erp.ecommerce;

import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.function.Function;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.server.handler.DefaultWebFilterChain;

public class Test {
	
	Authentication authentication;
	
	Function f = x -> x + "x";
	
}
