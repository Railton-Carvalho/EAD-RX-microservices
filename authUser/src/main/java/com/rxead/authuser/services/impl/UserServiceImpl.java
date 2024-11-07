package com.rxead.authuser.services.impl;

import com.rxead.authuser.repositories.UserRepository;
import com.rxead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
}
