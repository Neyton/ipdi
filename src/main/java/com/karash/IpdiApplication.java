package com.karash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = ".")
@EntityScan(basePackages = "/")
@ComponentScan(value = ".")
public class IpdiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpdiApplication.class, args);
	}
}
