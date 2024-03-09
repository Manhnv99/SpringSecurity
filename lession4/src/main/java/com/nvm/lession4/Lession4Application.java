package com.nvm.lession4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Lession4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lession4Application.class, args);
	}

}
