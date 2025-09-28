package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Account and customer microservice",
		description = "A microservice for performing CRUD ops for accounts and customers",
		version = "v1",
		contact = @Contact(
				name = "Anurag Semwal",
				email = "ansemw@gmail.com",
				url = "https://www.ansemw.com"
		),
		license = @License(
				name = "Apache2.0",
				url = "https://www.ansemw.com"

		))
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}


}
