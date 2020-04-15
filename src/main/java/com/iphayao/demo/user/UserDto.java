package com.iphayao.demo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iphayao.demo.validation.PasswordMatch;
import com.iphayao.demo.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatch
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String role;
}
