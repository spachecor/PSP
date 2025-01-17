package com.spachecor.cursosejercicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.spachecor.cursosejercicio.controller"})
public class CursosEjercicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosEjercicioApplication.class, args);
	}

}
