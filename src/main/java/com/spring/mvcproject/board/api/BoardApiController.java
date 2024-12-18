package com.spring.mvcproject.board.api;


import com.spring.mvcproject.board.entity.Board;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardApiController {
    private List<Board> boardList = new ArrayList<>();

    private Long idx = 0L;
    private LocalDateTime now = LocalDateTime.now();

    public BoardApiController() {

        Board board1 = new Board(idx++,"제목1","내용1",1,now);
        Board board2 = new Board(idx++,"제목1","내용1",1,now);
        Board board3 = new Board(idx++,"제목1","내용1",1,now);

        boardList.add(board1);
        boardList.add(board2);
        boardList.add(board3);
    }

    // 목록조회 get
    @GetMapping
    public List<Board> getListData(){

        return boardList;
    }

    //삭제 del


    //등록 post
}
