package com.erp.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.configuration.security.securitycontext.SecurityContextService;
import com.erp.ecommerce.model.user.profile.Customer;
import com.erp.ecommerce.repository.user.profile.CustomerRepository;

/**
 * TODO: VALIDATE AGAINST SECURITY CONTEXT!!!!
 */

@Service
public class MemberSvc {

	@Autowired
	CustomerRepository memberRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	SecurityContextService securityContextService;
	
	/**
	 * CRUD Operation
	 */

//	public Optional<Customer> getSelf() {
//		return Optional.of(securityContextService.getLoggedInCustomer());
//	}
//
//	public Optional<Customer> register (Customer member) {
//		member.setMid(null);
//		member.setPassword(passwordEncoder.encode(member.getPassword()));
//		return (!memberRepository.existsByNationalIdEquals(member.getNationalId())
//				&& !memberRepository.existsByUsernameEquals(member.getUsername()))
//				? Optional.of(memberRepository.save(member))
//				: Optional.empty();
//	}
//	
//
//	public Optional<Customer> updateSelf (Customer member) {
//		member.setPassword(member.getPassword());
//		return  (member.getUsername().equals(getLoggedInMember().getUsername()))
//				? Optional.of(memberRepository.save(member))
//				: Optional.empty();
//	}
//	
//	public Optional<Customer> deleteSelf() {
//		memberRepository.deleteById(getLoggedInMember().getMid());
//		return memberRepository.findById(getLoggedInMember().getMid());		
//	}
//	
//	/**
//	 * Validation
//	 */
//
//	public Boolean isValidUsername(Customer member) {
//		// TODO: 可在這邊納入其他驗證規則。
//		return !memberRepository.existsByUsernameEquals(member.getUsername());
//	}
//	
//	public Boolean isValidNationalId(Customer member) {
//		// TODO: 可在這邊納入其他驗證規則。
//		return !memberRepository.existsByNationalIdEquals(member.getNationalId());
//	}


}
