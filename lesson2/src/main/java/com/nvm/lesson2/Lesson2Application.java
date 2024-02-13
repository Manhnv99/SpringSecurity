package com.nvm.lesson2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Lesson2Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson2Application.class, args);
    }

}
