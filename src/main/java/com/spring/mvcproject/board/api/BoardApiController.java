package com.spring.mvcproject.board.api;


import com.spring.mvcproject.board.dto.request.BoardSaveDto;
import com.spring.mvcproject.board.entity.Board;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private List<Board> boardList = new ArrayList<>();

    private Long idx = 1L;

    public BoardApiController() {

//        Board board1 = new Board(idx++,"제목1","내용1",1,now);
//        Board board2 = new Board(idx++,"제목2","내용1",1,now);
//        Board board3 = new Board(idx++,"제목3","내용1",1,now);

//        Board board1 = Board.of(idx++,"제목1","내용1");
//        Board board2 = Board.of(idx++,"제목2","내용1");
//        Board board3 = Board.of(idx++,"제목3","내용1");
//
//        boardList.add(board1);
//        boardList.add(board2);
//        boardList.add(board3);
    }

    // 목록조회 get
    @GetMapping
    public List<Board> getListData(
            @RequestParam(required = false, defaultValue = "") String searchOption,
            @RequestParam(required = false, defaultValue = "") String keyword
    ) {
        Comparator<Board> comparing = Comparator.comparing(Board::getRegDateTime).reversed();
        Predicate<Board> filterOption = null;

        switch (searchOption) {
            case "title":
                filterOption = board -> board.getTitle().contains(keyword);
                break;
            case "content":
                filterOption = board -> board.getContent().contains(keyword);
                break;
//            case "writer":
//                filterOption = board -> board.getContent().contains(keyword);
//                break;
            case "tc":
                filterOption = board -> board.getContent().contains(keyword);
                break;
            default:
                filterOption = board -> true;
        }
        return boardList.stream()
                .filter(filterOption)
                .sorted(comparing)
                .collect(Collectors.toList());
    }

    //삭제 del
    @DeleteMapping("/{id}")
    public String deleteListData(
            @PathVariable Long id
    ) {

        Board selectedItem = null;
        for (Board board : boardList) {
            if (board.getId().equals(id)) {
                selectedItem = board;
                break;
            }
        }
        boardList.remove(selectedItem);

        return id + " 삭제";
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

            return ResponseEntity
                    .badRequest()    //400
                    .body(errorMap);
        }

        Board board = dto.toEntity();
        board.setId(idx++);

        boardList.add(board);

        return ResponseEntity
                .status(HttpStatus.OK)  //200
                // .body 는 json형태로 변환해서 반환시켜줌
                .body("성공");
    }

    //수정 (조회수)
    @PutMapping("/{id}")
    public String putViewCount(
            @PathVariable Long id
    ) {
        Board selectedItem = null;
        for (Board board : boardList) {
            if (board.getId().equals(id)) {
                selectedItem = board;
                selectedItem.setViewCount(selectedItem.getViewCount() + 1);
                break;
            }
        }


        return "조회수 1증가";
    }

}
