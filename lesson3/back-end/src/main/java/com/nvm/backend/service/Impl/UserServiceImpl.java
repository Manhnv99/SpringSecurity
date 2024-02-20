package com.nvm.backend.service.Impl;

import com.nvm.backend.entities.User;
import com.nvm.backend.repository.UserRepository;
import com.nvm.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getInfor() {
        Optional<User> user=userRepository.findUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        return user.orElse(null);
    }

    @Override
    public User getUserByUserName(String username) {
        Optional<User> user=userRepository.findUserByUserName(username);
        return user.orElse(null);
    }
}
