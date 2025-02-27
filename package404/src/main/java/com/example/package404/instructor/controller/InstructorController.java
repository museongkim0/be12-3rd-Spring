package com.example.package404.instructor.controller;


import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.instructor.model.dto.req.InstructorRequestDto;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.instructor.service.InstructorService;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {

    private final BaseResponseServiceImpl baseResponseService;


    private final InstructorService instructorService;
     private final InstructorRepository userRepository;

//    @GetMapping("/{instructorId}")
//    public ResponseEntity<InstructorResDto> getInstructor(@PathVariable Long instructorId) {
//        InstructorResDto instructor = instructorService.getInstructorById(instructorId);
//        return ResponseEntity.ok(instructor);
//    }


    //

    //Todo 강사가 자기 정보 불러오기
    //Todo 에러 처리 해줘야함
    @GetMapping("/edit/{instructorIdx}")
    public void getInstructor(@PathVariable Long instructorIdx) {
//    public BaseResponse<InstructorResponseDto> getInstructor(@PathVariable Long instructorIdx) {

        InstructorResponseDto response = instructorService.getInstructor(instructorIdx);


//        return baseResponseService.getSuccessResponse();
    }

    // 강사 정보 수정
    @PostMapping("/edit/{instructorIdx}")
    public void SetInfo(@RequestBody InstructorRequestDto dto  /*@AuthenticationPrincipal User user*/) {
        User user = userRepository.findById(3L).orElseThrow(() -> new RuntimeException("User not found")).getUser();

//        instructorService.setinfo(dto , user);
    }

    // Todo 학생이 강사정보 조회
    @GetMapping("/getinstructor")
    public void stdentgetInstructorInfo(){}





}
