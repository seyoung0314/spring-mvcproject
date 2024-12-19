package com.spring.mvcproject.chap2_5.score.dto.request;


import com.spring.mvcproject.chap2_5.score.entity.Score;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
// 클라이언트가 성적정보를 등록할 때
// 필요한정보만 담고, 입력값을 검증하는 객체
public class ScoreCreateDto {

    // 공백문자, null 둘다 허용하지 않음
    @NotEmpty(message = "이름은 필수값입니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "이름은 한글로만 작성하세요!")
    private String studentName;

    @Min(value = 0, message = "국어점수는 0 ~ 100점사이min")
    @Max(value = 100, message = "국어점수는 0 ~ 100점사이max")
    @NotNull(message = "국어 점수는 필수값입니다.")
    private Integer korean;

    @Min(value = 0, message = "영어점수는 0 ~ 100점사이min")
    @Max(value = 100, message = "영어점수는 0 ~ 100점사이max")
    @NotNull(message = "영어 점수는 필수값입니다.")
    private Integer english;

    @Min(value = 0, message = "수학점수는 0 ~ 100점사이min")
    @Max(value = 100, message = "수학점수는 0 ~ 100점사이max")
    @NotNull(message = "수학 점수는 필수값입니다.")
    private Integer math;

    public Score toEntity() {
        Score s = new Score();
        s.setName(this.studentName);
        s.setKor(this.korean);
        s.setEng(this.english);
        s.setMath(this.math);
        return s;
    }


}
