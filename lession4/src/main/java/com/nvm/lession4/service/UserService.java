package com.nvm.lession4.service;

import com.nvm.lession4.request.LoginRequest;
import com.nvm.lession4.request.RegisterRequest;
import com.nvm.lession4.response.AuthResponse;
import com.nvm.lession4.response.ResponseModal;

public interface UserService {

    ResponseModal register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);

}
