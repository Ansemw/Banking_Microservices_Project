package com.bank.loans;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(title = "Loans microservice",
				description = "A microservice for performing CRUD ops for Loans",
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
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
