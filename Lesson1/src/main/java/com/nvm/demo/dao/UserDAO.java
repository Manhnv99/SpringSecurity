package com.nvm.demo.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO {
    private final static List<UserDetails> APPLICATION_USERS= Arrays.asList(
            new User(
                    "nguyenvimanhnqt@gmail.com",
                    "manhdz123",
                    Collections.singletonList(new SimpleGrantedAuthority("USER_ADMIN"))
            ),
            new User(
                    "nguyenvimanhnqtt@gmail.com",
                    "manhdz123",
                    Collections.singletonList(new SimpleGrantedAuthority("USER_USER"))
            )
    );


    public UserDetails findUserByEmail(String email){
        return APPLICATION_USERS
                .stream()
                .filter(u->u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No User was not found!"));
    }
}
