package com.spring.mvcproject.chap2_5.score.api;

import com.spring.mvcproject.chap2_5.score.Service.ScoreService;
import com.spring.mvcproject.chap2_5.score.dto.response.ScoreDetailDto;
import com.spring.mvcproject.chap2_5.score.dto.response.ScoreListDto;
import com.spring.mvcproject.chap2_5.score.dto.request.ScoreCreateDto;
import com.spring.mvcproject.chap2_5.score.entity.Score;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@RestController
@RequestMapping("/api/v1/scores")
public class ScoreApiController {

    // 컨트롤러는 서비스에게 의존
    private ScoreService scoreService;

    @Autowired
    public ScoreApiController(ScoreService scoreService) {
        this.scoreService = scoreService;
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

        // 서비스에게 비지니스 로직 처리 위임
        List<ScoreListDto> responseList = scoreService.getList(sort);

        return ResponseEntity
                .ok()
                .body(responseList);
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

    //성적 상세조회 요청
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {

        // 서비스에게 단일 조회 관련처리를 위임
        try {
            ScoreDetailDto responseDto = scoreService.getDetail(id);

            return ResponseEntity.ok()
                    .body(responseDto);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }


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

        // 서비스에게 생성로직 처리 위임
        Score score = scoreService.create(dto);

        return ResponseEntity
                .ok()
                .body("성적 정보 생성 완료! " + score);

    }

    //성정 정보 삭제요청 처리
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScore(
            @PathVariable Long id
    ) {
        try {
            scoreService.remove(id);
            return ResponseEntity.ok()
                    .body("성적 정보 삭제 성공! - id = "+id);
        }catch (IllegalStateException e){
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

}
