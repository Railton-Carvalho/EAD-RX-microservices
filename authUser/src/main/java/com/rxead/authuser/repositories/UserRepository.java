package com.rxead.authuser.repositories;

import com.rxead.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existByUsername(String username);

    boolean existByEmail(String email);
}
