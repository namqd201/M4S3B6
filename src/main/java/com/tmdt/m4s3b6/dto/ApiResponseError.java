package com.tmdt.m4s3b6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponseError {
    private String message;
    private int status;
    private String timestamp;
}
