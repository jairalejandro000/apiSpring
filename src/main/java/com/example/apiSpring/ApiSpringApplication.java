package com.example.apiSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication //(exclude = SecurityAutoConfiguration.class)
public class ApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSpringApplication.class, args);
	}
}
