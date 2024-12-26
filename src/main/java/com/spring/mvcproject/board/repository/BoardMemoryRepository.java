package com.spring.mvcproject.board.repository;

import com.spring.mvcproject.board.dto.request.BoardSaveDto;
import com.spring.mvcproject.board.entity.Board;
import com.spring.mvcproject.board.service.BoardService;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Repository
// db 적용 안했을 때 사용했던 데이터리스트 (로컬)
public class BoardMemoryRepository implements BoardRepository {
    private List<Board> boardList = new ArrayList<>();

    private Long idx = 1L;

    public BoardMemoryRepository() {

        Board board1 = new Board(idx++, "제목1", "내용1", 1, LocalDateTime.now().minusYears(5));
//        Board board2 = new Board(idx++,"제목2","내용1",1,now);
//        Board board3 = new Board(idx++,"제목3","내용1",1,now);

//        Board board1 = Board.of(idx++,"제목1","내용1");
//        Board board2 = Board.of(idx++,"제목2","내용1");
//        Board board3 = Board.of(idx++,"제목3","내용1");
//
        boardList.add(board1);
//        boardList.add(board2);
//        boardList.add(board3);
    }


    @Override
    public List<Board> getBoardListAll() {
        return boardList;
    }

    @Override
    public List<Board> getBoardListAll(String sort, String keyword) {
        return List.of();
    }

    @Override
    public Board getBoardOne(Long id) {
        for (Board board : boardList) {
            if (board.getId().equals(id)) {
                return board;
            }
        }
        return null;
    }

    @Override
    public void postBoard(BoardSaveDto dto) {

        Board board = dto.toEntity();
        board.setId(idx++);

        boardList.add(board);
    }

    @Override
    public boolean deleteBoard(Long id) {
        Board deleted = null;
        for (Board board : boardList) {
            if (board.getId().equals(id)) {
                deleted = board;
                boardList.remove(board);
                return true;
            }
        }
        return false;
    }

    @Override
    public void viewCount(Long id) {
        Board selectedItem = null;
        for (Board board : boardList) {
            if (board.getId().equals(id)) {
                selectedItem = board;
                selectedItem.setViewCount(selectedItem.getViewCount() + 1);
                break;
            }
        }
    }

}
