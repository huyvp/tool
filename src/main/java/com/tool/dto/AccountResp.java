package com.tool.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountResp {
    String id;
    String type;
    String environment;
    String username;
    String password;
    String updatedBy;
    String updatedAt;
}
