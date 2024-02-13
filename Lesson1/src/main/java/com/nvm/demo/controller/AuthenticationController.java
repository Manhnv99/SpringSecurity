package com.nvm.demo.controller;

import com.nvm.demo.config.JWTUtil;
import com.nvm.demo.dao.UserDAO;
import com.nvm.demo.request.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDAO userDAO;
    private final JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        final UserDetails user= userDAO.findUserByEmail(authenticationRequest.getEmail());
        if(user!=null){
            return ResponseEntity.status(HttpStatus.OK).body(jwtUtil.generateToken(user));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some error has occurred!");
        }
    }
}
