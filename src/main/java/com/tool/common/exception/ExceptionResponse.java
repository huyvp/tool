package com.tool.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExceptionResponse {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Bangkok")
    private Date timestamp;
    private int code;
    private HttpStatus status;
    private String message;
}