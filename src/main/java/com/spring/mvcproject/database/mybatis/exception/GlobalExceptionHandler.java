package com.spring.mvcproject.database.mybatis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // 모든 예외를 처리하는 방법 (Exception을 상속받은 모든 예외 처리)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        // 예외가 발생하면 500 Internal Server Error 상태 코드와 메시지 반환
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }

}
