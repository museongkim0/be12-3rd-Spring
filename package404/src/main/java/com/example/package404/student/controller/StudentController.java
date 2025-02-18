package com.example.package404.student.controller;

import com.example.package404.student.model.Dto.StudentRegisterDto;
import com.example.package404.student.model.Dto.StudentResponseDto;
import com.example.package404.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/read/{idx}")
    public ResponseEntity<StudentResponseDto> read(@PathVariable Long idx) {
        StudentResponseDto response = studentService.read(idx);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentResponseDto>> list() {
        List<StudentResponseDto> boardResList = studentService.list();
        return ResponseEntity.ok(boardResList);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody StudentRegisterDto dto) {
        studentService.register(dto);

        return ResponseEntity.ok("success");
    }
}
