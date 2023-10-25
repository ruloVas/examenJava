package com.examen.examenJava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExamenJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenJavaApplication.class, args);
	}

}
