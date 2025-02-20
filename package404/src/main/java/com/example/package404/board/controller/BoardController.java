package com.example.package404.board.controller;

import com.example.package404.board.model.Dto.BoardPageResponse;
import com.example.package404.board.model.Dto.BoardReadResponseDto;
import com.example.package404.board.model.Dto.BoardRequestDto;
import com.example.package404.board.model.Dto.BoardResponseDto;
import com.example.package404.board.service.BoardService;
import com.example.package404.response.BaseResponse;
import com.example.package404.response.BaseResponseServiceImpl;
import com.example.package404.response.BaseResponseStatus;
import com.example.package404.response.CommonResponseStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final BaseResponseServiceImpl baseResponseService;
    @PostMapping("/register/{boardType}")
    public BaseResponse<Object> register(@RequestBody BoardRequestDto boardRequestDto, @PathVariable int boardType) {
        BoardResponseDto response = boardService.register(boardRequestDto, boardType);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.CREATED);
    }

    @GetMapping("/read/{boardIdx}")
    public BaseResponse<Object> read(@PathVariable Long boardIdx) {
        BoardReadResponseDto response = boardService.read(boardIdx);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @GetMapping("/list/{boardType}")
    public BaseResponse<Object> getBoardList(@PathVariable int boardType,int page, int size) {
        BoardPageResponse response = boardService.getBoardList(boardType, page, size);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

}
