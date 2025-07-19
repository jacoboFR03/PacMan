package com.pacman;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
	info = @Info(
		title = "PACMAN API",
		version = "1.0.0",
		description = "API para ordenar camisetas en base a múltiples criterios ponderados."
	),
	servers = {
		@Server(url = "http://localhost:8080/pacman")
	}
)
@SpringBootApplication
public class PacmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacmanApplication.class, args);
	}

}
