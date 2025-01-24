package com.phonenumber.restapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class PhoneNumberRestAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneNumberRestAPIApplication.class, args);
	}

}
