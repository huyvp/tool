package com.tool.password;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Password {
    private Long id;
    @NotBlank(message = "Environment is require")
    private String environment;
    @NotBlank(message = "Type is require")
    private String type;
    @NotBlank(message = "Username is require")
    private String username;
    @NotBlank(message = "Password is require")
    private String password;
    private String updatedBy;
    private Timestamp UpdatedAt;
}
