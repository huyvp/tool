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
public class TabReq {
    @NotBlank(message = "Name tab is require")
    String name;
    String icon;
    @NotBlank(message = "Url tab is require")
    String url;
    int level;
}
