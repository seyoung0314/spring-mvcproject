package com.spring.mvcproject.chap6_3.exception.dto;

import com.spring.mvcproject.chap6_3.exception.ErrorCode;
import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
public class MemberException extends RuntimeException {
    private final ErrorCode errorCode;
    public MemberException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    public MemberException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
