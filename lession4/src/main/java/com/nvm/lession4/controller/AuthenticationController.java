package com.nvm.lession4.controller;


import com.nvm.lession4.request.LoginRequest;
import com.nvm.lession4.request.RegisterRequest;
import com.nvm.lession4.response.ResponseModal;
import com.nvm.lession4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        ResponseModal responseModal = userService.register(registerRequest);
        return new ResponseEntity<>(responseModal.getMessage(),responseModal.getHttpStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequest));
    }


}
