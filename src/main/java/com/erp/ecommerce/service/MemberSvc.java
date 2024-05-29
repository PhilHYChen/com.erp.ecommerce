package com.erp.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.model.authentication.MemberUserDetails;
import com.erp.ecommerce.model.user.profile.Customer;
import com.erp.ecommerce.repository.profile.CustomerRepo;

/**
 * TODO: VALIDATE AGAINST SECURITY CONTEXT!!!!
 */

@Service
public class MemberSvc {

	@Autowired
	CustomerRepo memberRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * CRUD Operation
	 */

	public Optional<Customer> getSelf() {
		return memberRepo.findById(getLoggedInMember().getMid());
	}

	public Optional<Customer> register (Customer member) {
		member.setMid(null);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		return (!memberRepo.existsByNationalIdEquals(member.getNationalId())
				&& !memberRepo.existsByUsernameEquals(member.getUsername()))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	

	public Optional<Customer> updateSelf (Customer member) {
		member.setPassword(member.getPassword());
		return  (member.getUsername().equals(getLoggedInMember().getUsername()))
				? Optional.of(memberRepo.save(member))
				: Optional.empty();
	}
	
	public Optional<Customer> deleteSelf() {
		memberRepo.deleteById(getLoggedInMember().getMid());
		return memberRepo.findById(getLoggedInMember().getMid());		
	}
	
	/**
	 * Validation
	 */

	public Boolean isValidUsername(Customer member) {
		// TODO: 可在這邊納入其他驗證規則。
		return !memberRepo.existsByUsernameEquals(member.getUsername());
	}
	
	public Boolean isValidNationalId(Customer member) {
		// TODO: 可在這邊納入其他驗證規則。
		return !memberRepo.existsByNationalIdEquals(member.getNationalId());
	}
	
	private MemberUserDetails getLoggedInMember() {
		return (MemberUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
