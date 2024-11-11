package com.tool.service.weekly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue {
    private String key;
    private String summary;
    private String status;
    private String createdAt;
    private String updatedAt;
}
