package com.example.package404.board.service;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.dto.BoardPageResponse;
import com.example.package404.board.model.dto.BoardReadResponseDto;
import com.example.package404.board.model.dto.BoardRequestDto;
import com.example.package404.board.model.dto.BoardResponseDto;
import com.example.package404.board.repository.BoardRepository;
import com.example.package404.global.exception.BoardException;
import com.example.package404.global.response.responseStatus.BoardResponseStatus;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponseDto register(User loginUser,BoardRequestDto boardRequestDto, int boardType) {
        if (boardRequestDto == null) {
            throw new BoardException(BoardResponseStatus.INVALID_BOARD_ID); // 잘못된 요청 데이터
        }

        try {
            Board board = boardRepository.save(boardRequestDto.toEntity(loginUser, boardType));
            return BoardResponseDto.from(board);
        } catch (Exception e) {
            throw new BoardException(BoardResponseStatus.BOARD_CREATION_FAILED); // 게시글 저장 실패
        }
    }

    public BoardReadResponseDto read(Long boardIdx) {
        if (boardIdx == null || boardIdx <= 0) {
            throw new BoardException(BoardResponseStatus.INVALID_BOARD_ID); // 유효하지 않은 ID
        }

        Board board = boardRepository.getBoardByIdx(boardIdx);
        if (board == null) {
            throw new BoardException(BoardResponseStatus.BOARD_NOT_FOUND); // 게시글 없음
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

        Page<Board> boardList = boardRepository.findAllByBoardType(PageRequest.of(page, size), boardType);
        if (boardList.isEmpty()) {
            throw new BoardException(BoardResponseStatus.BOARD_NOT_FOUND); // 게시판에 게시글 없음
        }

        return BoardPageResponse.from(boardList);
    }
}
