package com.spring.mvcproject.chap2_5.score.api;

import com.spring.mvcproject.chap2_5.score.dto.reponse.ScoreListDto;
import com.spring.mvcproject.chap2_5.score.dto.request.ScoreCreateDto;
import com.spring.mvcproject.chap2_5.score.entity.Score;
import jakarta.servlet.jsp.el.NotFoundELResolver;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreApiController {
    private Map<Long, Score> scoreStore = new HashMap<>();

    private Long nextId = 1L;

    public ScoreApiController() {
        Score s1 = new Score(nextId++, "철수", 100, 100, 100);
        Score s2 = new Score(nextId++, "맹구", 55, 95, 15);
        Score s3 = new Score(nextId++, "유리", 4, 100, 40);

        scoreStore.put(s1.getId(), s1);
        scoreStore.put(s2.getId(), s2);
        scoreStore.put(s3.getId(), s3);
    }


    // 전체 성적조회 (정렬 파라미터를 읽어야 함)
    // /api/v1/scores?sort=name
//    @GetMapping
//    public List<Score> scoreList(
//            @RequestParam(required = false, defaultValue = "") String sort
//    ) {
//
//        List<Score> result = new ArrayList<>(scoreStore.values())
//                .stream()
//                .sorted(getScoreComparator(sort))
//                .collect(Collectors.toList());
//        return result;
//    }

    @GetMapping
    public ResponseEntity<List<ScoreListDto>> scoreList(
            @RequestParam(required = false, defaultValue = "") String sort
    ) {

//        ArrayList<ScoreListDto> responseList = new ArrayList<>();
//
//        ArrayList<Score> originalScores = new ArrayList<>(scoreStore.values());
//        for (Score score : originalScores) {
//            ScoreListDto dto = new ScoreListDto(score);
//            responseList.add(dto);
//        }

        List<ScoreListDto> responseList = new ArrayList<>(scoreStore.values())
                .stream()
                .map(score ->
                        new ScoreListDto(score))
                .collect(Collectors.toList());

        calculatorRank(responseList);

        responseList.sort(getScoreComparator(sort));

        return ResponseEntity
                .ok()
                .body(responseList);
    }

    private void calculatorRank(List<ScoreListDto> responseList) {
        responseList.sort(comparing(ScoreListDto::getAverage).reversed());

        for (ScoreListDto dto : responseList) {
            dto.setRank(responseList.indexOf(dto) + 1);
        }
    }


    private static Comparator<ScoreListDto> getScoreComparator(String sort) {
        Comparator<ScoreListDto> comparing = comparing(ScoreListDto::getId);

        switch (sort) {
            case "id":
                comparing = comparing(ScoreListDto::getId);
                break;
            case "name":
                comparing = comparing(ScoreListDto::getMaskingName);
                break;
            case "average":
                comparing = Comparator.comparingDouble(ScoreListDto::getAverage).reversed();
                break;
            default:
                break;
        }
        return comparing;
    }

//    //http://localhost:9000/api/v1/scores?name=짱구&kor=10&eng=30&math=60
//    // 학생 추가
//    @PostMapping
//    public String addStudent(
//            @RequestBody Score score
////        String name,
////        int kor,
////        int eng,
////        int math
//    ) {
//        // 여기서 검증을 하면 단일책임원칙 위반....
//        score.setId(nextId++);

    /// /        Score newStudent = new Score(nextId++, name, kor, eng, math);
//        scoreStore.put(score.getId(), score);
//        return "";
//    }

//http://localhost:9000/api/v1/scores?name=짱구&kor=10&eng=30&math=60
// 학생 추가
    @PostMapping
    public ResponseEntity<?> addStudent(
            @RequestBody @Valid ScoreCreateDto dto
            , BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {  //입력값 검증에서 에러가 발생했다면
            System.out.println("=======================================");

            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });


            return ResponseEntity
                    .badRequest()
                    .body(errorMap)
                    ;

        }
        // ScoreCreateDto 를 Score로 변환하는 작업
        Score score = dto.toEntity();
        score.setId(nextId++);

        scoreStore.put(score.getId(), score);
        return ResponseEntity
                .ok()
                .body("성적 정보 생성 완료! " + score);

    }

    //성정 정보 삭제요청 처리
    @DeleteMapping("/{id}")
    public String deleteScore(
            @PathVariable Long id
    ) {
        scoreStore.remove(id);
        return id + "삭제 성공";
    }

}
