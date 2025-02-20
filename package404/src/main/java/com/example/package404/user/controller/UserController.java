package com.example.package404.user.controller;

import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import com.example.package404.user.service.UserService;
import com.example.package404.user.model.Dto.UserRequestDto;
import com.example.package404.user.model.Dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    // 역할에 따라 회원가입 처리
    @PostMapping("/{role}/signup")
    public ResponseEntity<UserResponseDto.SignupResponse> roleBasedSignup(
            @PathVariable String role,
            @RequestBody UserRequestDto.SignupRequest dto) {

        // role 검증
        if (!isValidRole(role)) {
            return ResponseEntity.badRequest().body(null);  // 잘못된 role 값 처리
        }

        UserResponseDto.SignupResponse response = userService.signup(dto, roles);
        return ResponseEntity.ok(response);
    }

    // role이 유효한지 검사하는 메서드
    private boolean isValidRole(String role) {
        return role.equals("student") || role.equals("instructor") || role.equals("manager");
    }
}

