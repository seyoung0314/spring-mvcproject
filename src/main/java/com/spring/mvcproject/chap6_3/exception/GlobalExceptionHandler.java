package com.spring.mvcproject.chap6_3.exception;

import com.spring.mvcproject.chap6_3.exception.dto.ErrorResponse;
import com.spring.mvcproject.chap6_3.exception.dto.MemberException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

// api에서 발생하는 수많은 예외상황들을 도맡아서 처리하는 aop클래스
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 모든 예외를 처리하는 방법 (Exception을 상속받은 모든 예외 처리)
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> handleException(
            MemberException e
            , HttpServletRequest request
            ) {


        // 구체적인 에러 객체 생성
        ErrorResponse error = ErrorResponse.builder()
                .path(request.getRequestURI())
                .message(e.getMessage())
                .error(e.getStatus().getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .status(e.getStatus().value())
                .build();

        // 로깅 처리
       log.warn("exception occurred! caused by : {}",e.getMessage());

        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }

    // 입력값 검증 관련 예외처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBindingError(MethodArgumentNotValidException e
            , HttpServletRequest request
    ) {

        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        // 로깅 처리
        log.warn("exception occurred! caused by : {}",message);

        // 구체적인 에러 객체 생성
        ErrorResponse error = ErrorResponse.builder()
                .path(request.getRequestURI())
                .message(message)
                .error(e.getStatusCode().toString())
                .timestamp(LocalDateTime.now())
                .status(e.getStatusCode().value())
                .build();

        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleProductNotFound() {
//        return ResponseEntity
//                .status(404)
//                .body("10 nn ");
//    }
//
//    @ExceptionHandler(HttpServerErrorException.class)
//    public ResponseEntity<String> handleInternalServerError() {
//        return ResponseEntity
//                .status(500)
//                .body("admin nono~~ ");
//    }


}
