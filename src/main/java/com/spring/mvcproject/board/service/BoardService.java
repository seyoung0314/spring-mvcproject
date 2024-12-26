package com.spring.mvcproject.board.service;

import com.spring.mvcproject.board.dto.request.BoardSaveDto;
import com.spring.mvcproject.board.dto.response.BoardDetailDto;
import com.spring.mvcproject.board.dto.response.BoardListDto;
import com.spring.mvcproject.board.entity.Board;
import com.spring.mvcproject.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardListDto> getList(String searchOption, String keyword) {
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
                filterOption = board ->board.getContent().contains(keyword)
                                       || board.getTitle().contains(keyword);
                break;
            default:
                filterOption = board -> true;
        }

        List<Board> filteredList = boardRepository.getBoardListAll().stream()
                .filter(filterOption)
                .sorted(comparing)
                .collect(Collectors.toList());

        List<BoardListDto> boardListDtos = filteredList.stream()
                .map(board -> new BoardListDto(board))
                .collect(Collectors.toList());

        return boardListDtos;
    }

    public BoardDetailDto getDetail(Long id) {

        Board targetBoard = new Board();
        for (Board board : boardRepository.getBoardListAll()) {
            if (board.getId().equals(id)) {
                targetBoard = board;
            }
        }
        if(targetBoard == null){
            throw new IllegalStateException("게시글 정보가 없습니다.");
        }
        BoardDetailDto responseDto = new BoardDetailDto(targetBoard);
        System.out.println(responseDto);

        return responseDto;
    }

    public void deleteBoard(Long id) {
        boolean deleted = boardRepository.deleteBoard(id);
        if (!deleted) {
            throw new IllegalStateException("삭제 할 게시글이 없습니다.");
        }
    }

    public void postBoard(BoardSaveDto dto) {
        boardRepository.postBoard(dto);
    }

    public void putBoard(Long id) {
        System.out.println("보드 서비스 조회수");
        boardRepository.viewCount(id);
    }
}
