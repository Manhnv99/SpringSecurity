package com.nvm.lesson2.service.serviceImpl;

import com.nvm.lesson2.repository.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var appUser= appUserRepo.findByUserName(username)
                .orElseThrow(()->new RuntimeException("UserName Not Found"));
        return new User(appUser.getUserName(),appUser.getPassWord(),appUser.getAuthorities());
    }
}
