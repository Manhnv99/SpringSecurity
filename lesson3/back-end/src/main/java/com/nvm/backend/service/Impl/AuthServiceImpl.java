package com.nvm.backend.service.Impl;

import com.nvm.backend.entities.User;
import com.nvm.backend.exception.ExceptionMessage;
import com.nvm.backend.repository.RoleRepository;
import com.nvm.backend.repository.UserRepository;
import com.nvm.backend.request.LoginRequest;
import com.nvm.backend.request.SignUpRequest;
import com.nvm.backend.response.AuthResponse;
import com.nvm.backend.service.AuthService;
import com.nvm.backend.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            Optional<User> userOptional=userRepository.findUserByUserName(loginRequest.getUsername());
            return new AuthResponse(JwtUtils.generateToken(userOptional.get()),userOptional.get());
        }catch (Exception e){
            throw new ExceptionMessage("UserName or Password was wrong!");
        }
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        if(userRepository.findUserByUserName(signUpRequest.getUsername()).isPresent()){
            throw new ExceptionMessage("UserName was exists!");
        }
        User user=new User();
        //Encode
        String passWordEncode=passwordEncoder.encode(signUpRequest.getPassword());
        //createNew
        user.setName(signUpRequest.getName());
        user.setUserName(signUpRequest.getUsername());
        user.setPassword(passWordEncode);
        user.setRole(roleRepository.getReferenceById(signUpRequest.getRoleId()));
        return userRepository.save(user);
    }
}
