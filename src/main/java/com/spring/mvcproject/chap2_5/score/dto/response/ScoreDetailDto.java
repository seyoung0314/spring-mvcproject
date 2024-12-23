package com.spring.mvcproject.chap2_5.score.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.mvcproject.chap2_5.score.entity.Score;
import lombok.*;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScoreDetailDto {
    private Long id;
    @JsonProperty("fullName")
    private String name;
    private int kor, eng, math;
    private int total;
    private double average;
    @Setter
    private int rank;
    private int totalCount;

    public ScoreDetailDto(Score score, int totalCount) {
        this.id = score.getId();
        this.name = score.getName();
        this.kor = score.getKor();
        this.eng = score.getEng();
        this.math = score.getMath();
        this.total = score.getKor() + score.getEng() + score.getMath();
        this.average = Math.round(this.total / 3.0 * 100) /100.0;
        this.totalCount = totalCount;
    }
}
