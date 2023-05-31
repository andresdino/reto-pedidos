package com.pragma.powerup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
public class PowerUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerUpApplication.class, args);
	}

}
