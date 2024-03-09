package com.nvm.lession4.service.impl;

import com.nvm.lession4.entity.Role;
import com.nvm.lession4.entity.User;
import com.nvm.lession4.repository.UserRepository;
import com.nvm.lession4.request.LoginRequest;
import com.nvm.lession4.request.RegisterRequest;
import com.nvm.lession4.response.AuthResponse;
import com.nvm.lession4.response.ResponseModal;
import com.nvm.lession4.service.UserService;
import com.nvm.lession4.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserDetailsService userDetailsService;

    @Override
    public ResponseModal register(RegisterRequest registerRequest) {
        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        for (Role role : Role.values()){
            if(registerRequest.getRole().equals(role.name())){
                user.setRole(role);
            }
        }
        userRepository.save(user);
        return new ResponseModal(HttpStatus.CREATED,"Đăng ký tài khoản thành công");
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        Map<String,Object> extraInfor = new HashMap<>();
        extraInfor.put("userInfor",userDetails);
        String token = jwtUtils.generateToken(extraInfor,userDetails);
        return new AuthResponse(token);
    }
}
