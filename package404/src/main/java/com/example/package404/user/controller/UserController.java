package com.example.package404.user.controller;

import com.example.package404.user.service.UserService;
import com.example.package404.user.model.Dto.UserRequestDto;
import com.example.package404.user.model.Dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto.SignupResponse> signup(@RequestBody UserRequestDto.SignupRequest dto) {
        return ResponseEntity.ok(userService.signup(dto));
    }

    @PostMapping("/instructor/signup")
    public ResponseEntity<UserResponseDto.SignupResponse> instructorSignup(@RequestBody UserRequestDto.SignupRequest dto) {
        return ResponseEntity.ok(userService.signup(dto));
    }

    @PostMapping("/manager/signup")
    public ResponseEntity<UserResponseDto.SignupResponse> managerSignup(@RequestBody UserRequestDto.SignupRequest dto) {
        return ResponseEntity.ok(userService.signup(dto));
    }

    @PostMapping("/student/signup")
    public ResponseEntity<UserResponseDto.SignupResponse> studentSignup(@RequestBody UserRequestDto.SignupRequest dto) {
        return ResponseEntity.ok(userService.signup(dto));
    }
}
