package com.example.package404.instructor.controller;


import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.instructor.model.dto.req.InstructorRequestDto;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.instructor.service.InstructorService;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

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
    @GetMapping("/edit/{instructorIdx}")
        public BaseResponse<InstructorResponseDto> getInstructor (@PathVariable Long instructorIdx){

            InstructorResponseDto response = instructorService.getInstructor(instructorIdx);


            return baseResponseService.getSuccessResponse(response , InstructorResponseStatus.SUCCESS);
        }

        // 강사 정보 수정
        @PostMapping("/edit/{instructorIdx}")
        public void SetInfo (@PathVariable Long instructorIdx, @RequestBody InstructorRequestDto dto  /*@AuthenticationPrincipal User user*/){
            User user = userRepository.findById(3L).orElseThrow(() -> new RuntimeException("User not found")).getUser();

                instructorService.setInfo(instructorIdx, dto , user);
        }

        // Todo 학생이 강사정보 조회
        // 학생이 어디까지 강사의 정보를 가질수 있는가?
        @GetMapping("/getinstructor")
        public void stdentgetInstructorInfo () {


        }

        @GetMapping("/instructors")
        public BaseResponse<List<InstructorResponseDto>> getAllInstructorInfo () {
            List<InstructorResponseDto> responseDtoList = instructorService.instructor_list();


            return baseResponseService.getSuccessResponse(responseDtoList, InstructorResponseStatus.SUCCESS);
        }



}
