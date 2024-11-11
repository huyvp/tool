package com.tool.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdate {
    @NotBlank(message = "Id is require")
    String id;
    @NotBlank(message = "Password is require")
    String password;
    @NotBlank(message = "Environment is require")
    String environment;
}
