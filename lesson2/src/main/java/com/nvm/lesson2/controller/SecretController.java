package com.nvm.lesson2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secret")
public class SecretController {

    @GetMapping("/")
    public ResponseEntity<?> getSecret(){
        System.out.println("run99");
        return ResponseEntity.status(HttpStatus.OK).body("hi hello");
    }
}
