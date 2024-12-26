package com.spring.mvcproject.board.repository;

import com.spring.mvcproject.board.dto.request.BoardSaveDto;
import com.spring.mvcproject.board.entity.Board;
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
        return jdbcTemplate.update("""
                DELETE FROM tbl_board
                WHERE id = ?
                """, id) > 0;
    }

    @Override
    public void viewCount(Long id) {
        System.out.println("db주입");
        jdbcTemplate.update("""
                UPDATE tbl_board
                SET view_count = view_count + 1
                WHERE id = ?
                """,id);
    }
}
