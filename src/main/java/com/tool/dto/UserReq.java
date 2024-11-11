package com.tool.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserReq {
    @NotBlank(message = "Username is require")
    String username;
    @NotBlank(message = "Password is require")
    String password;
    @NotBlank(message = "Knox id is require")
    String knoxId;
}
