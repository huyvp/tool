package com.tool.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @NotBlank(message = "Username is require")
    String username;
    @NotBlank(message = "Password is require")
    String password;
    @NotBlank(message = "Email id is require")
    String email;
}
