package com.spring.mvcproject.board.repository;

import com.spring.mvcproject.board.dto.request.BoardSaveDto;
import com.spring.mvcproject.board.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository {

    List<Board> getBoardListAll();

    Board getBoardOne(Long id);

    void postBoard(BoardSaveDto dto);

    boolean deleteBoard(Long id);

    void viewCount(Long id);
}
