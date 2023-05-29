package com.healthcare.todohealthcare.dto.commenResponse.exception.exception;

import com.healthcare.todohealthcare.dto.commenResponse.exception.errorCode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {
    private final ErrorCode errorCode;
}
