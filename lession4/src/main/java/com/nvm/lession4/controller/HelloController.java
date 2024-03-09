package com.nvm.lession4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/infor")
public class HelloController {

    @GetMapping("/forAdmin")
    public ResponseEntity<?> inforAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body("hello ADMIN");
    }

    @GetMapping("/forUser")
    public ResponseEntity<?> inforUser(){
        return ResponseEntity.status(HttpStatus.OK).body("hello User");
    }
}
