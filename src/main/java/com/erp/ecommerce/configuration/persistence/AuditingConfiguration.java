package com.erp.ecommerce.configuration.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "accountAuditorAware")
public class AuditingConfiguration {

}
