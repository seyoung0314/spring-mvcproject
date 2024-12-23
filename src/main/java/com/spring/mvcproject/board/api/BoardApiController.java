package com.spring.mvcproject.board.api;


import com.spring.mvcproject.board.dto.request.BoardSaveDto;
import com.spring.mvcproject.board.dto.response.BoardDetailDto;
import com.spring.mvcproject.board.dto.response.BoardListDto;
import com.spring.mvcproject.board.entity.Board;
import com.spring.mvcproject.board.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardApiController {

    BoardService boardService;

    @Autowired
    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 목록조회 get
    @GetMapping
    public ResponseEntity<List<BoardListDto>> getListData(
            @RequestParam(required = false, defaultValue = "") String searchOption,
            @RequestParam(required = false, defaultValue = "") String keyword
    ) {

        List<BoardListDto> boardListDtos = boardService.getList(searchOption, keyword);

        return ResponseEntity
                .ok()
                .body(boardListDtos);
    }

    // 목록 디테일 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> detailData(
            @PathVariable Long id
    ) {
        BoardDetailDto responseDto = boardService.getDetail(id);

        return ResponseEntity
                .ok()
                .body(responseDto);
    }


    //삭제 del
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteListData(
            @PathVariable Long id
    ) {
        try {
            boardService.deleteBoard(id);
            return ResponseEntity.ok()
                    .body("삭제되었습니다. id : " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    //등록 post
    @PostMapping
    public ResponseEntity<?> postListData(
            @RequestBody @Valid BoardSaveDto dto
            , BindingResult bindingResult
    ) {
        //        String textOnly = board.getContent().replaceAll("<[^>]*>", "");
        System.out.println("dto = " + dto);

        //입력값 검증 응답 처리 (aop위반 - 나중에 리팩토링할 예정)
        if (bindingResult.hasErrors()) {
            System.out.println("================================");

            Map<String, String> errorMap = new HashMap<>();

            // .getFieldError 은 첫번째 에러만 반환
//            FieldError fieldError = bindingResult.getFieldError();
//            System.out.println("fieldError = " + fieldError);

            // .getFieldErrors 에러들을 리스트로 반환
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            int errorCount = bindingResult.getErrorCount() - 1;

            errorMap.put("errorCount", String.valueOf(errorCount));

            for (String s : errorMap.keySet()) {
                System.out.println("--------------------------------");
                System.out.println("s = " + s);
                System.out.println("errorMap.get(s) = " + errorMap.get(s));
            }
            System.out.println(errorCount + "개 실패");

            return ResponseEntity.badRequest().body(errorMap);
        }
        boardService.postBoard(dto);

        return ResponseEntity.ok().body("등록성공");
}

    //수정 (조회수)
    @PutMapping("/{id}")
    public ResponseEntity<?> putViewCount(
            @PathVariable Long id
    ) {
        try {
            boardService.putBoard(id);
            return ResponseEntity.ok().body("조회수 1증가");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
