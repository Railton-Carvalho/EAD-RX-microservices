package com.rxead.authuser.services;

import com.rxead.authuser.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

    Optional<UserModel> findById(UUID userId);

    boolean findByUsername(String username);

    void delete(UserModel userModel);

    void save(UserModel userModel);

    boolean findByEmail(String email);
}
