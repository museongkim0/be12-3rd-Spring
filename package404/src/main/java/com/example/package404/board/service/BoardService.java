package com.example.package404.board.service;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.Dto.BoardPageResponse;
import com.example.package404.board.model.Dto.BoardReadResponseDto;
import com.example.package404.board.model.Dto.BoardRequestDto;
import com.example.package404.board.model.Dto.BoardResponseDto;
import com.example.package404.board.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;
    public BoardResponseDto register(BoardRequestDto boardRequestDto, int boardType) {
        Board board = boardJpaRepository.save(boardRequestDto.toEntity(boardType));
        return BoardResponseDto.from(board);
    }

    public BoardReadResponseDto read(Long boardIdx) {
        Board board = boardJpaRepository.getBoardByIdx(boardIdx);
        return BoardReadResponseDto.from(board);
    }

    public BoardPageResponse getBoardList(int boardType, int page, int size) {
        Page<Board> boardList = boardJpaRepository.findAllByBoardType(PageRequest.of(page, size), boardType);
        return BoardPageResponse.from(boardList);
    }
}
