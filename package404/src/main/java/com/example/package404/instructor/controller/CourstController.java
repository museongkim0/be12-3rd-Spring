package com.example.package404.instructor.controller;

import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.res.InstructorCourseListResponseDto;
import com.example.package404.instructor.model.dto.res.CourseResponseDto;
import com.example.package404.instructor.repository.CourseRepository;
import com.example.package404.instructor.service.CourseService;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    // -> 리스트 반환하는데 해당 강사가 했던 코스만 가져오는거라 세부 내용 필요 x
    // 삭제 해도 될것같기도?
    @GetMapping("/list")
    public ResponseEntity<List<InstructorCourseListResponseDto>> list() {
        List<InstructorCourseListResponseDto> response = courseService.list();

        return ResponseEntity.ok(response);
    }



    // 강사가 맡았던 기수 조회
    @GetMapping("/instructor/{userIdx}")
    public ResponseEntity<List<InstructorCourseListResponseDto> > getInstructorCourse(@PathVariable Long userIdx) {
        List<InstructorCourseListResponseDto> response = courseService.findIstructorCourse(userIdx);

        return ResponseEntity.ok(response);
    }

    //Todo 페이지로 처리할건데 총 교과목 단위로 조회해야함







    // 이건 현재 기수들 코스 조회하는거
    @GetMapping("/{generation}")
    public ResponseEntity<CourseResponseDto> read(@PathVariable int generation) {
        CourseResponseDto response = courseService.read(generation);
        return ResponseEntity.ok(response);
    }
    //



}
