package com.spring.mvcproject.board.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.mvcproject.board.entity.Board;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BoardDetailDto {
    private Long id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    public BoardDetailDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
//        this.content = removeTags(board.getContent());
        this.date = board.getRegDateTime();
    }
    private String removeTags(String tagContent){
        // 정규식을 사용하여 HTML 태그 제거
        return tagContent.replace("<p>", "").replace("</p>", "<br>");
    }
}
