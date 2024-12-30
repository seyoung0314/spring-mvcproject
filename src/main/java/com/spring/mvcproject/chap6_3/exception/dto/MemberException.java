package com.spring.mvcproject.chap6_3.exception.dto;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
public class MemberException extends RuntimeException {
    private HttpStatus status;

    public MemberException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
