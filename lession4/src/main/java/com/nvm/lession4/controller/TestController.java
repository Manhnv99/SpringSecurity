package com.nvm.lession4.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(
                authentication.getAuthorities()
        );
        System.out.println(
                authentication.getPrincipal()
        );
        System.out.println(
                authentication.getCredentials()
        );
        return "hello";
    }

}
