package com.nvm.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/data")
public class myController {

    @GetMapping("/firstdata")
    public ResponseEntity<?> myData1(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("firstdata");
    }

    @GetMapping("/seconddata")
    public ResponseEntity<?> myData2(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("seconddata");
    }
}
