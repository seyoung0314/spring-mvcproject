package com.spring.mvcproject.board.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Board {
    private Long id; //글 번호
    private String title; //글 제목
    private String content; //내용
    private int viewCount; //조회수
    private LocalDateTime  regDateTime;  //시간

}
