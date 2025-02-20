package com.example.package404.instructor.controller;


import com.example.package404.instructor.model.dto.req.InstructorRequestDto;
import com.example.package404.instructor.service.InstructorService;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {



    private final InstructorService instructorService;

//    @GetMapping("/{instructorId}")
//    public ResponseEntity<InstructorResDto> getInstructor(@PathVariable Long instructorId) {
//        InstructorResDto instructor = instructorService.getInstructorById(instructorId);
//        return ResponseEntity.ok(instructor);
//    }
    @PostMapping("/setinfo")
    public void SetInfo(@RequestBody InstructorRequestDto dto , @AuthenticationPrincipal User user) {

        instructorService.setinfo(dto , user);



    }







}
