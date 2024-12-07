package com.rxead.authuser.services.impl;

import com.rxead.authuser.models.UserModel;
import com.rxead.authuser.repositories.UserRepository;
import com.rxead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {
        return userRepository.findById(userId);
    }



    @Override
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }

    @Override
    public void save(UserModel userModel) {
        userRepository.save(userModel);
    }

    @Override
    public boolean findByUsername(String username) {
        return userRepository.existByUsername(username);
    }

    @Override
    public boolean findByEmail(String email) {
        return userRepository.existByEmail(email);
    }
}
