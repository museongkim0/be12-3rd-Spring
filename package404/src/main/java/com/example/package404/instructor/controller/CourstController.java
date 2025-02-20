package com.example.package404.instructor.controller;

import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.res.CourseResDto;
import com.example.package404.instructor.service.CourseService;
import com.example.package404.user.model.User;
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




    @PostMapping("/register")
    public void register(@AuthenticationPrincipal User user, @RequestBody CourseRegister dto) {
        courseService.register(dto, user);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseResDto>> list() {
        List<CourseResDto> response = courseService.list();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{courseIdx}")
    public ResponseEntity<CourseResDto> read(@PathVariable Long courseIdx) {
        CourseResDto  response = courseService.read(courseIdx);
        return ResponseEntity.ok(response);
    }

}
