package com.spring.mvcproject.chap2_5.score.entity;


import lombok.*;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
//학생 한명의 성적정보를 저장
public class Score {
    private Long id; // 학번
    private String name;
    private int kor, eng, math; // 국영수 점수
}
