package com.rxead.authuser.services;

import com.rxead.authuser.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserModel> findAll();

    Optional<UserModel> findById(UUID userId);

    boolean findByUsername(String username);

    void delete(UserModel userModel);

    void save(UserModel userModel);

    boolean findByEmail(String email);
}
