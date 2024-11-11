package com.tool.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountReq {
    String id;
    @NotBlank(message = "Environment is require")
    String environment;
    @NotBlank(message = "Type is require")
    String type;
    @NotBlank(message = "Username is require")
    String username;
    @NotBlank(message = "Password is require")
    String password;
}
