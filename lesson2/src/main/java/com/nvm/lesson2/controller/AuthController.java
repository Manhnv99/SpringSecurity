package com.nvm.lesson2.controller;

import com.nvm.lesson2.request.AuthRequestDto;
import com.nvm.lesson2.response.AuthResponseDTO;
import com.nvm.lesson2.service.AuthService;
import com.nvm.lesson2.utils.AuthStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequestDto){
        System.out.println("run3");
        var jwtToken = authService.login(authRequestDto.getUsername(),authRequestDto.getPassword());
        if(jwtToken.equalsIgnoreCase("UserName Not Found")){
            return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(jwtToken, AuthStatus.LOGIN_FAILED));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(jwtToken, AuthStatus.LOGIN_SUCCESS));
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody AuthRequestDto authRequestDto){
        try{
            var jwtToken=authService.signUp(authRequestDto.getName(),authRequestDto.getUsername(),authRequestDto.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(jwtToken,AuthStatus.USER_CREATED_SUCCESSFULLY));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthResponseDTO(e.getMessage(),AuthStatus.USER_NOT_CREATED));
        }
    }
}
