package com.erp.ecommerce.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.stereotype.Service;

import com.erp.ecommerce.model.user.account.Account;
import com.erp.ecommerce.model.user.profile.Customer;
import com.erp.ecommerce.repository.user.account.AccountRepository;
import com.erp.ecommerce.repository.user.profile.CustomerRepository;

import jakarta.validation.Valid;

@Service
public class RegistrationService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CompromisedPasswordChecker compromisedPasswordChecker;
	
	public Optional<Account> register(@Valid Account account) {
		format(account);
		return (isValidAccount(account))
				? Optional.of(accountRepository.save(account))
				: Optional.empty();
	}

	public boolean isAvailableUsername(String string) {
		return !accountRepository.existsByUsername(string);
	}
	
	public boolean isNotCompromisedPassword(String string) {
		return !compromisedPasswordChecker.check(string).isCompromised();
	}
	
	public boolean isAvailableNationalId(String string) {
		return !customerRepository.existsByNationalId(string);
	}
	
	public boolean isAvailableEmail(String string) {
		return !customerRepository.existsByEmail(string);
	}
	
	/**
	 * Utilities (private)
	 */
	
	private void format(Account account) {
		// Account fields...
		account.setUsername(account.getUsername().toLowerCase());
		// Customer fields...
		Customer customer = account.getCustomer();
		customer.setNationalId(customer.getNationalId().toUpperCase());
		customer.setEmail(customer.getEmail().toLowerCase());
	}
	
	private boolean isValidAccount(Account account) {
		return Optional.of(account)
				/**
				 * Apply all relevant validation logics...
				 */
				.filter(this::isValidUsername)
				.filter(this::isAvailableUsername)
				.filter(this::isValidPassword)
				.filter(this::isNotCompromisedPassword)
				.filter(this::isValidNationalId)
				.filter(this::isAvailableNationalId)
				.filter(this::isValidEmail)
				.filter(this::isAvailableEmail)				
				.isPresent();
	}
	
	private boolean isValidUsername(Account account) {
		return Pattern.compile("^[A-Za-z]{1}[A-Za-z0-9.]{2,63}$")
				.matcher(account.getUsername())
				.matches();
	}
	
	private boolean isAvailableUsername(Account account) {
		return !accountRepository.existsByUsername(account.getUsername());
	}
	
	private boolean isValidPassword(Account account) {
		/**
		 * Adapted from OWASP Validation Regex Repository.
		 * See: https://owasp.org/www-community/OWASP_Validation_Regex_Repository
		 */
		return Pattern.compile(
				"^(?:(?=.*\\\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\\\1{2,})[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()\\\\|\\\\[\\\\]\\\\-\\\\$\\\\^\\\\@\\\\/]{12,128}$")
				.matcher(account.getPasswordHash())
				.matches();
	}
	
	private boolean isNotCompromisedPassword(Account account) {
		return !compromisedPasswordChecker.check(account.getPasswordHash()).isCompromised();
	}
	
	private boolean isValidNationalId(Account account) {
		String nationalId = account.getCustomer().getNationalId();
		return Pattern.compile("^[A-Za-z]{1}[1-2]{1}[0-9]{8}$")
				.matcher(nationalId)
				.matches()
				&& nationalId.charAt(9) == (
						(NationalIdLetterValue.getValueByKey(String.valueOf(nationalId.charAt(0)))
								+ nationalId.charAt(1) * 8.
								+ nationalId.charAt(2) * 7
								+ nationalId.charAt(3) * 6
								+ nationalId.charAt(4) * 5 
								+ nationalId.charAt(5) * 4
								+ nationalId.charAt(6) * 3
								+ nationalId.charAt(7) * 2
								+ nationalId.charAt(8) * 1
								) % 10);
	}
	
	private boolean isAvailableNationalId(Account account) {
		return !customerRepository.existsByNationalId(account.getCustomer().getNationalId());
	}
	
	private boolean isValidEmail(Account account) {
		/**
		 * Baeldung's adaption from OWASP Validation Regex Repository.
		 * See:
		 * 1. https://www.baeldung.com/java-email-validation-regex#10-gmail-special-case-for-emails
		 * 2. https://owasp.org/www-community/OWASP_Validation_Regex_Repository
		 */
		return Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$")
			      .matcher(account.getCustomer().getEmail())
			      .matches(); 
	}
	
	private boolean isAvailableEmail(Account account) {
		return !customerRepository.existsByEmail(account.getCustomer().getEmail());
	}
	
	private enum NationalIdLetterValue {

	    A(1), B(0), C(9), D(8), E(7), F(6), G(5), H(4), I(9), J(3),
	    K(2), L(2), M(1), N(0), O(8), P(9), Q(8), R(7), S(6), T(5),
	    U(4), V(3), W(1), X(3), Y(2), Z(0);

	    private final int value;
	    private static final Map<String, NationalIdLetterValue> lookup = new HashMap<>();

	    static {
	        for (NationalIdLetterValue letter : NationalIdLetterValue.values()) {
	            lookup.put(letter.name(), letter);
	        }
	    }

	    NationalIdLetterValue(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }

	    public static int getValueByKey(String key) {
	        NationalIdLetterValue letter = lookup.get(key.toUpperCase());
	        if (letter != null) {
	            return letter.getValue();
	        }
	        throw new IllegalArgumentException("Invalid key: " + key);
	    }
	}
}
