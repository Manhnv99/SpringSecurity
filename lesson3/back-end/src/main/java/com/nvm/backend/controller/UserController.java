package com.nvm.backend.controller;

import com.nvm.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/data")
    public ResponseEntity<?> getData(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getInfor());
    }
}
