package com.spring.mvcproject.chap2_5.score.dto.reponse;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.mvcproject.chap2_5.score.entity.Score;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ScoreListDto {

    private Long id; // 식별번호
    private String maskingName;

    @JsonProperty("sum")
    private int total; // 총점

    @JsonProperty("avg")
    private double average; // 평균 (소숫점 2자리)
    private int rank;

    //생성자를 통해 엔터티 데이터를 받아 처리
    public ScoreListDto(Score score) {

        this.id = score.getId();
        this.maskingName = makeMaskingName(score.getName());
        this.total = score.getKor() + score.getEng() + score.getMath();
        this.average = Math.round((this.total / 3.0) * 100) / 100.0;
        this.rank = 1;

    }

    private String getOriginName(Score score){
        return score.getName();
    }
    /**
     * 원본 이름을 첫글자와 마지막 글자만 보여주고 나머지 *처리
     * 만약 이름이 2글자라면 첫글자만 보여주고 두번째 글자는 *처리
     *
     * @param originName - 마스킹이 안된 원본 이름
     * @return - 마스킹된 이름
     */
    private String makeMaskingName(String originName) {
        // 이름이 2글자인 경우
        if (originName.length() <= 2) {
            return originName.charAt(0) + "*";
        }
        // 나머지 경우
        char firstLetter = originName.charAt(0);
        char lastLetter = originName.charAt(originName.length() - 1);

        String maskinName = String.valueOf(firstLetter);
        for (int i = 1; i < originName.length() - 1; i++) {
            maskinName += "*";
        }
        maskinName += lastLetter;
        return maskinName;

    }
}
