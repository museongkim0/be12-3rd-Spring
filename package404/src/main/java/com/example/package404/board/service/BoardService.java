package com.example.package404.board.service;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.dto.BoardPageResponse;
import com.example.package404.board.model.dto.BoardReadResponseDto;
import com.example.package404.board.model.dto.BoardRequestDto;
import com.example.package404.board.model.dto.BoardResponseDto;
import com.example.package404.board.repository.BoardJpaRepository;
import com.example.package404.global.exception.BoardException;
import com.example.package404.global.response.responseStatus.BoardResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;

    public BoardResponseDto register(BoardRequestDto boardRequestDto, int boardType) {
        if (boardRequestDto == null) {
            throw new BoardException(BoardResponseStatus.INVALID_POST_ID); // 잘못된 요청 데이터
        }

        try {
            Board board = boardJpaRepository.save(boardRequestDto.toEntity(boardType));
            return BoardResponseDto.from(board);
        } catch (Exception e) {
            throw new BoardException(BoardResponseStatus.POST_CREATION_FAILED); // 게시글 저장 실패
        }
    }

    public BoardReadResponseDto read(Long boardIdx) {
        if (boardIdx == null || boardIdx <= 0) {
            throw new BoardException(BoardResponseStatus.INVALID_POST_ID); // 유효하지 않은 ID
        }

        Board board = boardJpaRepository.getBoardByIdx(boardIdx);
        if (board == null) {
            throw new BoardException(BoardResponseStatus.POST_NOT_FOUND); // 게시글 없음
        }

        return BoardReadResponseDto.from(board);
    }

    public BoardPageResponse getBoardList(int boardType, int page, int size) {
        if (boardType < 0) {
            throw new BoardException(BoardResponseStatus.INVALID_PAGE); // 잘못된 게시판 타입
        }
        if (page < 0 || size <= 0) {
            throw new BoardException(BoardResponseStatus.INVALID_PAGE); // 잘못된 페이지 요청
        }

        Page<Board> boardList = boardJpaRepository.findAllByBoardType(PageRequest.of(page, size), boardType);
        if (boardList.isEmpty()) {
            throw new BoardException(BoardResponseStatus.BOARD_NOT_FOUND); // 게시판에 게시글 없음
        }

        return BoardPageResponse.from(boardList);
    }
}
