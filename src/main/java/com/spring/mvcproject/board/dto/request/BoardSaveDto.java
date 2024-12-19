package com.spring.mvcproject.board.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BoardSaveDto {

    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요.")
    //한글도 한 글자다 1씩 계산
    @Size(min = 10, message = "내용을 10글자 이상 입력해주세요.")
    private String content;


}
