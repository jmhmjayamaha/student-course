package com.interview.codingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CodingtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingtestApplication.class, args);
	}

}
