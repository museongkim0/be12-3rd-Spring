package com.example.package404.comment.controller;

import com.example.package404.comment.model.dto.CommentRequestDto;
import com.example.package404.comment.service.CommentService;
import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseService;
import com.example.package404.global.response.responseStatus.BaseResponseStatus;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final BaseResponseService baseResponseService;


    @PostMapping("/register/{boardIdx}")
    // 작성자 이름 받아오는 코드 추가 해야함
    public BaseResponse<Object> register(@PathVariable int boardIdx, @RequestBody CommentRequestDto dto) {
        commentService.register(dto);
        return baseResponseService.getSuccessResponse(CommonResponseStatus.CREATED);
    }

}
