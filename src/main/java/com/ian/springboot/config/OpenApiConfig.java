package com.ian.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("RESTful API with Spring Boot").version("v1")
				.description("Project to train").termsOfService("https://ian.spring.com.br")
				.license(new License().name("Apache 2.0").url("https://ian.spring.com.br")));
	}
}