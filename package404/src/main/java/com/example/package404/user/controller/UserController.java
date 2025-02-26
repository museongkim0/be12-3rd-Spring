package com.example.package404.user.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
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
    public BaseResponse<Object> signup(@RequestBody UserRequestDto.SignupRequest dto, @PathVariable String role) {
        UserResponseDto.SignupResponse response = userService.signup(dto, role);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

}
