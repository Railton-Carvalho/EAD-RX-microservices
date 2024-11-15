package com.rxead.authuser.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    public interface UserView{
        public static interface RegistrationPost{}
        public static interface UserUpdate {}
        public static interface PasswordUpdate {}
        public static interface ImageUpdate {}
    }

    private UUID userId;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserUpdate.class})
    @JsonView({UserView.RegistrationPost.class, UserView.UserUpdate.class})
    private String username;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserUpdate.class})
    @Size(min = 4, max = 50)
    @JsonView({UserView.RegistrationPost.class, UserView.UserUpdate.class})
    private String password;

    @Email()
    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserUpdate.class})
    @JsonView({UserView.RegistrationPost.class, UserView.UserUpdate.class})
    private String email;

    @NotBlank(groups = {UserView.PasswordUpdate.class})
    @Size(min = 4, max = 50)
    @JsonView({UserView.PasswordUpdate.class})
    private String oldPassword;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserUpdate.class})
    @JsonView({UserView.RegistrationPost.class, UserView.UserUpdate.class})
    private String fullName;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserUpdate.class})
    @JsonView({UserView.RegistrationPost.class, UserView.UserUpdate.class})
    private String phoneNumber;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.UserUpdate.class})
    @Size(max = 11)
    @JsonView({UserView.RegistrationPost.class, UserView.UserUpdate.class})
    private String cpf;

    @NotBlank(groups = UserView.ImageUpdate.class)
    @JsonView({UserView.ImageUpdate.class})
    private String imageUrl;


}
