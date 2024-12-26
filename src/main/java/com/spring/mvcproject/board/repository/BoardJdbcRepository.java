package com.spring.mvcproject.board.repository;

import com.spring.mvcproject.board.dto.request.BoardSaveDto;
import com.spring.mvcproject.board.entity.Board;
import com.spring.mvcproject.board.entity.PostStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardJdbcRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Board> getBoardListAll() {
        return jdbcTemplate.query("""
                SELECT * FROM tbl_board
                WHERE status = 'ACTIVE'
                """, new BeanPropertyRowMapper<>(Board.class));
    }

    @Override
    public Board getBoardOne(Long id) {
        return jdbcTemplate.queryForObject("""
                SELECT * FROM tbl_board
                WHERE id = ?
                """, new BeanPropertyRowMapper<>(Board.class), id);
    }

    @Override
    public void postBoard(BoardSaveDto dto) {
        jdbcTemplate.update("""
                INSERT INTO tbl_board
                     (title,content)
                VALUES
                     (?,?)
                """, dto.getTitle(), dto.getContent());
    }

    @Override
    public boolean deleteBoard(Long id) {
        System.out.println("-----------------delete--------");
        return jdbcTemplate.update("""
                UPDATE tbl_board
                SET status = ?
                WHERE id = ?
                """, PostStatus.DELETED.name(),id) > 0;
        // enum은 enum상수 타입이라서 db에 보낼 때  string 타입으로 형변환
    }

    @Override
    public void viewCount(Long id) {
        jdbcTemplate.update("""
                UPDATE tbl_board
                SET view_count = view_count + 1
                WHERE id = ?
                """, id);
    }
}
