package com.bank.cards;

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
		info = @Info(title = "Cards microservice",
				description = "A microservice for performing CRUD ops for Cards",
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
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
