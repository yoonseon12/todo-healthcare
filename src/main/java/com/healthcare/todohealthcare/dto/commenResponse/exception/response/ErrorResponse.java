package com.healthcare.todohealthcare.dto.commenResponse.exception.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private LocalDateTime timestemp;
    private int status;
    private HttpStatus error;
    private String code;
    private String message;
    private String messageContent;
}
