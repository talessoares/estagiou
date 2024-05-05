package com.lab.estagiou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Estagiou API", version = "v1", description = "API for management of students and companies"))
public class EstagiouApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstagiouApplication.class, args);
	}

}
