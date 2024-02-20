package com.nvm.backend.service;

import com.nvm.backend.entities.User;
import com.nvm.backend.request.LoginRequest;
import com.nvm.backend.request.SignUpRequest;
import com.nvm.backend.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    User signUp(SignUpRequest signUpRequest);
}
