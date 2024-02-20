package com.nvm.lesson2.service;

public interface AuthService {
    String login(String name, String password);

    String signUp(String name, String username, String password);
}
