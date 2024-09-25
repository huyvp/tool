package com.tool.password;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PasswordDto {
    private Long id;
    private String environment;
    private String type;
    private String username;
    private String password;
    private String updatedBy;
    private String UpdatedAt;
}
