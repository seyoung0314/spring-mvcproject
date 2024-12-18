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

    //정적 팩토리 메서드 :  객체를 생성할 때 자동으로 들어가는 필드를 제외하고
    //      객체를 빠르게 생성해주는 메서드
    public static Board of(Long id, String title, String content){
        Board board = new Board();
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);
        board.setViewCount(0);
        board.setRegDateTime(LocalDateTime.now());

        return board;
    }
}
