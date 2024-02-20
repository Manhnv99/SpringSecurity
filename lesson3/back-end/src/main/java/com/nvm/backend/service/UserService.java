package com.nvm.backend.service;

import com.nvm.backend.entities.User;

public interface UserService {
    User getInfor();
    User getUserByUserName(String username);
}
