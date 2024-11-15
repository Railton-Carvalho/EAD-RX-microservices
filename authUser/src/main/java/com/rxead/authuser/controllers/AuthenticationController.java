package com.rxead.authuser.controllers;


import com.rxead.authuser.dtos.UserDto;
import com.rxead.authuser.enums.UserStatus;
import com.rxead.authuser.enums.UserType;
import com.rxead.authuser.models.UserModel;
import com.rxead.authuser.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;


    @PostMapping("/singup")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        var userModel = new UserModel();

        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdatedDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);

        return null;
    }

}
