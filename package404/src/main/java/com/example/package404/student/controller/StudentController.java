package com.example.package404.student.controller;

import com.example.package404.student.model.Dto.StudentDetailRegisterDto;
import com.example.package404.student.model.Dto.StudentDetailResponseDto;
import com.example.package404.student.model.Dto.StudentResponseDto;
import com.example.package404.student.service.StudentService;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@AuthenticationPrincipal User user, @RequestBody StudentDetailRegisterDto dto) {
//        User user = new User();
//        user.setIdx(2L);
//        user.setEmail("test2@test.com");
//        user.setPassword("qwer1234");
//        user.setName("lee");
//        user.setRole("ROLE_STUDENT");
        studentService.register(dto, user);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/read/{idx}")
    public ResponseEntity<StudentResponseDto> read(@PathVariable Long idx) {
        StudentResponseDto response = studentService.read(idx);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentResponseDto>> list() {
        List<StudentResponseDto> studentResList = studentService.list();
        return ResponseEntity.ok(studentResList);
    }

    @GetMapping("/update/{action}")
    public ResponseEntity<String> update(/*@AuthenticationPrincipal User user,*/ @PathVariable String action) {
        User user = new User();
        user.setIdx(2L);
        user.setEmail("test2@test.com");
        user.setPassword("qwer1234");
        user.setName("lee");
        user.setRole("ROLE_STUDENT");
        studentService.update(user, action);

        return ResponseEntity.ok("success");
    }
}
