package com.erp.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.configuration.security.securitycontext.CurrentUserService;
import com.erp.ecommerce.configuration.security.securitycontext.annotation.CurrentUserProfile;
import com.erp.ecommerce.model.user.profile.AbstractUserProfile;
import com.erp.ecommerce.model.user.profile.Customer;
import com.erp.ecommerce.repository.user.profile.CustomerRepository;

/**
 * TODO: VALIDATE AGAINST SECURITY CONTEXT!!!!
 */
/**
 * A generic service for operations pertaining to all AbstractUserProfile subclasses. 
 * @param <T>
 */
@Service
public class UserProfileService<T extends AbstractUserProfile> {

	
	/**
	 * CRUD Operation
	 */

	public Optional<T> getUserProfile(T userProfile) {
		
		/**
		 * Perform validation, removal of sensitive information...etc. here... 
		 */
		
		return Optional.of(userProfile);
	}
//
//	
//
//	public Optional<Customer> register (Customer member) {
//		member.setMid(null);
//		member.setPassword(passwordEncoder.encode(member.getPassword()));
//		return (!customerRepository.existsByNationalIdEquals(member.getNationalId())
//				&& !customerRepository.existsByUsernameEquals(member.getUsername()))
//				? Optional.of(customerRepository.save(member))
//				: Optional.empty();
//	}
//	
//
//	public Optional<Customer> updateSelf (Customer member) {
//		member.setPassword(member.getPassword());
//		return  (member.getUsername().equals(getLoggedInMember().getUsername()))
//				? Optional.of(customerRepository.save(member))
//				: Optional.empty();
//	}
//	
//	public Optional<Customer> deleteSelf() {
//		customerRepository.deleteById(getLoggedInMember().getMid());
//		return customerRepository.findById(getLoggedInMember().getMid());		
//	}
//	
//	/**
//	 * Validation
//	 */
//
//	public Boolean isValidUsername(Customer member) {
//		// TODO: 可在這邊納入其他驗證規則。
//		return !customerRepository.existsByUsernameEquals(member.getUsername());
//	}
//	
//	public Boolean isValidNationalId(Customer member) {
//		// TODO: 可在這邊納入其他驗證規則。
//		return !customerRepository.existsByNationalIdEquals(member.getNationalId());
//	}


}
