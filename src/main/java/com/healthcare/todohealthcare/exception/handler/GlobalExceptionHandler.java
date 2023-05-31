package com.healthcare.todohealthcare.exception.handler;

import com.healthcare.todohealthcare.exception.errorCode.CommonErrorCode;
import com.healthcare.todohealthcare.exception.exception.RestApiException;
import com.healthcare.todohealthcare.exception.response.ErrorResponse;
import com.healthcare.todohealthcare.exception.errorCode.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleCustomException(RestApiException e) {
        ErrorCode errorCode = e.getErrorCode();
        return handleExceptionInternal(errorCode, e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, e);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception e) {
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode, e);
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, Exception e) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, e));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, Exception e) {
        return ErrorResponse.builder()
                .timestemp(LocalDateTime.now())
                .status(errorCode.getHttpStatus().value())
                .error(errorCode.getHttpStatus())
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .messageContent(e.getMessage())
                .build();
    }
}
