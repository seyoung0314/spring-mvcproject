package com.spring.mvcproject.board.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.mvcproject.board.entity.Board;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BoardListDto {

    private Long bno;  // 원본 게시물 번호
    private String shortTitle; // 5글자 이상 줄임 처리된 제목
    private String shortContent; // 15자 이상 줄임 처리된 글 내용

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime date;    // 포맷팅된 날짜
    private int view; // 조회수
    private boolean newArticle; // 1시간 이내 게시물인가?


    public BoardListDto(Board board) {
        this.bno = board.getId();
        this.shortTitle = transShortTitle(board.getTitle());
        this.shortContent = truncateHtmlText(board.getContent(),15);
//        this.date = fomattedDate(board.getRegDateTime());
        this.date = board.getRegDateTime();
        this.view = board.getViewCount();
        this.newArticle = isNew(board.getRegDateTime());
    }

    private boolean isNew(LocalDateTime articleTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(articleTime, now);

        return duration.toHours() < 2 && !articleTime.isAfter(now);
    }

    private String fomattedDate(LocalDateTime regDateTime) {
        // yyyy/MM/dd 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return regDateTime.format(formatter);
    }

    private String transShortContent(String content) {

        return  content.length() > 15 ? content.substring(0, 15)+"..." : content;
    }

    //이거 중간에 글자 색 변경했을 경우 수정해야함
    // 게시판 글이 <p><span>태그를 포함하고 있어서 작성한 글만 줄이기
    public static String truncateHtmlText(String html, int maxLength) {
        // HTML 태그를 모두 제거한 텍스트만 추출
        String text = html.replaceAll("<[^>]*>", "");

        // 텍스트 길이가 maxLength를 초과하면 자르고 '...'을 추가
        if (text.length() > maxLength) {
            text = text.substring(0, maxLength) + "...";
        }

        // HTML을 복원하여 텍스트만 수정
        String truncatedHtml = html.replaceAll(">([^<]+)<", ">" + text + "<");

        System.out.println("truncatedHtml = " + truncatedHtml);

        String[] paragraphs = truncatedHtml.split("(?=<p>)");

        return paragraphs[0];
    }


    private String transShortTitle(String title) {
        return  title.length() > 5 ? title.substring(0, 5)+"..." : title;
    }


}
