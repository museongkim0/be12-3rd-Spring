package com.example.package404.user.controller;

import com.example.package404.global.exception.UserException;
import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.global.response.responseStatus.UserResponseStatus;
import com.example.package404.user.service.UserService;
import com.example.package404.user.model.Dto.UserRequestDto;
import com.example.package404.user.model.Dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;
    private final BaseResponseServiceImpl baseResponseService;

    @PostMapping("/signup/{role}")
    public ResponseEntity<BaseResponse<Object>> signup(@RequestBody UserRequestDto.SignupRequest dto, @PathVariable String role) {
        try {
            UserResponseStatus userSUCCESS = UserResponseStatus.SUCCESS;
            UserResponseDto.SignupResponse response = userService.signup(dto, role);
            BaseResponse baseResponse = BaseResponse.builder()
                    .code(userSUCCESS.getCode())
                    .message(userSUCCESS.getMessage())
                    .isSuccess(userSUCCESS.isSuccess())
                    .data(response)
                    .build();

            return ResponseEntity.ok(baseResponse);
        } catch (UserException e) {
            return ResponseEntity.badRequest().body(new BaseResponse<>());
        }
    }

}
