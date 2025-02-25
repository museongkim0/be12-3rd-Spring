package com.example.package404.user.controller;

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

    @PostMapping("/signup/{role}")
    public ResponseEntity<String> signup(@RequestBody UserRequestDto.SignupRequest dto, String role) {
        try {
            UserResponseDto.SignupResponse response = userService.signup(dto, role);
            return ResponseEntity.status(201).body("회원가입이 완료되었습니다." + response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("서버 오류가 발생했습니다.");
        }
    }

}
