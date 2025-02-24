package com.example.package404.instructor.controller;

import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.res.CourseResponseDto;
import com.example.package404.instructor.repository.CourseRepository;
import com.example.package404.instructor.service.CourseService;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourstController {


    private final CourseService courseService;

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;


    @PostMapping("/register")
    public void register(/*@AuthenticationPrincipal User user,*/ @RequestBody CourseRegister dto) {

        //임시 유저 생성
        User user = userRepository.findById(3L).orElseThrow(() -> new RuntimeException("User not found"));

        courseService.register(dto, user);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseResponseDto>> list() {
        List<CourseResponseDto> response = courseService.list();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{courseIdx}")
    public ResponseEntity<CourseResponseDto> read(@PathVariable Long courseIdx) {
        CourseResponseDto response = courseService.read(courseIdx);
        return ResponseEntity.ok(response);
    }



}
