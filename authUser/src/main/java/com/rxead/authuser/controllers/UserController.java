package com.rxead.authuser.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.rxead.authuser.dtos.UserDto;
import com.rxead.authuser.enums.UserStatus;
import com.rxead.authuser.enums.UserType;
import com.rxead.authuser.models.UserModel;
import com.rxead.authuser.services.UserService;
import com.rxead.authuser.specifications.SpecificationTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(
            SpecificationTemplate.UserSpec spec
            ,Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(spec, pageable));
    }

    @PostMapping
    public ResponseEntity<Object> registrerUser(@RequestBody @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {
        if (userService.findByUsername(userDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken!");
        }
        if (userService.findByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdatedDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }


    @GetMapping("{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId")UUID userId){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");

        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID userId){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        
        userService.delete(userModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("User Deleted successful");
    }
}
