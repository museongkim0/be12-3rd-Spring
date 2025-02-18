package com.example.package404.board.repository;

import com.example.package404.board.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {
    Board getBoardByIdx(Long idx);

    Page<Board> findAllByBoardType(PageRequest of, int boardType);
}
