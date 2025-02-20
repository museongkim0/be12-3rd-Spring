package com.example.package404.board.service;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.Dto.BoardPageResponse;
import com.example.package404.board.model.Dto.BoardReadResponseDto;
import com.example.package404.board.model.Dto.BoardRequestDto;
import com.example.package404.board.model.Dto.BoardResponseDto;
import com.example.package404.board.repository.BoardJpaRepository;
import com.example.package404.exception.BoardException;
import com.example.package404.response.BaseResponseServiceImpl;
import com.example.package404.response.BoardResponseStatus;
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
        if (board == null) {  // 게시글이 없으면 예외 발생 (6002번 에러)
            throw new BoardException(BoardResponseStatus.POST_NOT_FOUND);
        }
        return BoardReadResponseDto.from(board);
    }

    public BoardPageResponse getBoardList(int boardType, int page, int size) {
        Page<Board> boardList = boardJpaRepository.findAllByBoardType(PageRequest.of(page, size), boardType);
        return BoardPageResponse.from(boardList);
    }
}
