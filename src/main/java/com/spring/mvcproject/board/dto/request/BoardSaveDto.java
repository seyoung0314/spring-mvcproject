package com.spring.mvcproject.board.dto.request;


import com.spring.mvcproject.board.entity.Board;
import com.spring.mvcproject.chap2_5.score.entity.Score;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
// DTO에서는 클라이언트의 데이터를 객체에 세팅하기위해
// ALL생성자나 SETTER를 반드시 구현해놔야함
// 실무적 측면 - SETTER 구현은 불변성을 해칠 수 있으므로 신중하게 선택
// (Setter는 처음부터 안만들고 꼭 필요하면 만들기)
public class BoardSaveDto {

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    //한글도 한 글자당 1씩 계산
    @Size(min = 10, message = "내용을 10글자 이상 입력해주세요.")
    private String content;


    public Board toEntity() {
        Board board = new Board();
        board.setTitle(this.title);
        board.setContent(this.content);
        board.setViewCount(0);
        board.setRegDateTime(LocalDateTime.now());

        return board;
    }
}
