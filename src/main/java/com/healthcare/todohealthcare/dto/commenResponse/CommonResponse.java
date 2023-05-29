package com.healthcare.todohealthcare.dto.commenResponse;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommonResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private HttpStatus code;
    private T data;

    public static <T> CommonResponse of(T data) {
        return CommonResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK)
                .data(data)
                .build();
    }
}
