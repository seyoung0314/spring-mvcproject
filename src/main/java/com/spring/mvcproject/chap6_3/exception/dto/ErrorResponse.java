package com.spring.mvcproject.chap6_3.exception.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status; // 에러 상태코드
    private String error;   // 에러이름
    private String message; // 에러의 구체적인 원인 메세지
    private String path;
}
