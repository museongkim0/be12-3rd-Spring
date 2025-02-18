package com.example.package404.board.controller;

import com.example.package404.board.model.Dto.BoardPageResponse;
import com.example.package404.board.model.Dto.BoardReadResponseDto;
import com.example.package404.board.model.Dto.BoardRequestDto;
import com.example.package404.board.model.Dto.BoardResponseDto;
import com.example.package404.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/register/{boardType}")
    public ResponseEntity<BoardResponseDto> register(@RequestBody BoardRequestDto boardRequestDto, @PathVariable int boardType) {
        BoardResponseDto response = boardService.register(boardRequestDto, boardType);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/read/{boardIdx}")
    public ResponseEntity<BoardReadResponseDto> read(@PathVariable Long boardIdx) {
        BoardReadResponseDto response = boardService.read(boardIdx);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/{boardType}")
    public ResponseEntity<BoardPageResponse> getBoardList(@PathVariable int boardType,int page, int size) {
        BoardPageResponse response = boardService.getBoardList(boardType, page, size);
        return ResponseEntity.ok(response);
    }

}
