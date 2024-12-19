package com.spring.mvcproject.chap2_5.score.dto.request;


import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
// 클라이언트가 성적정보를 등록할 때
// 필요한정보만 담고, 입력값을 검증하는 객체
public class ScoreCreateDto {
    private String sName;
    private int korean;
    private int english;
    private int math;


}
