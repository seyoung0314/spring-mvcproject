package com.spring.mvcproject.board.api;


import com.spring.mvcproject.board.entity.Board;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    ){
        Comparator<Board> comparing = Comparator.comparing(Board::getRegDateTime).reversed();
        Predicate<Board> filterOption = null;

        switch (searchOption){
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
    ){

        Board selectedItem = null;
        for (Board board : boardList) {
            if(board.getId().equals(id)){
                selectedItem = board;
                break;
            }
        }
        boardList.remove(selectedItem);

        return id+" 삭제";
    }
    //등록 post
    @PostMapping
    public String postListData(
            @RequestBody Board board
    ){
//        String textOnly = board.getContent().replaceAll("<[^>]*>", "");
        System.out.println("board = " + board);

        board.setId(idx++);
        board.setRegDateTime(LocalDateTime.now());

        boardList.add(board);
        return "추가성공";
    }

    //수정 (조회수)
    @PutMapping("/{id}")
    public String putViewCount(
            @PathVariable Long id
    ){
        Board selectedItem = null;
        for (Board board : boardList) {
            if(board.getId().equals(id)){
                selectedItem = board;
                selectedItem.setViewCount(selectedItem.getViewCount()+1);
                break;
            }
        }


        return "조회수 1증가";
    }

}
