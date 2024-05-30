package com.erp.ecommerce.repository.utility;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.ecommerce.model.utility.PostalCode;

public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {

}
