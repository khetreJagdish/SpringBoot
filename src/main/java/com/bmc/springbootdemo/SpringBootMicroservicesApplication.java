package com.bmc.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMicroservicesApplication {

	public static void main(String[] args) {
		helloworld();
		SpringApplication.run(SpringBootMicroservicesApplication.class, args);
	}

	private static void helloworld() {
		System.out.println("Hello World from Spring Boot");
	}

}
