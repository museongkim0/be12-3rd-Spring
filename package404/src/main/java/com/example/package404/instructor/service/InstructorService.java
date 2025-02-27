package com.example.package404.instructor.service;


import com.example.package404.global.exception.BaseException;
import com.example.package404.global.exception.InstructorException;
import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.responseStatus.BaseResponseStatus;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.req.InstructorRequestDto;
import com.example.package404.instructor.model.dto.req.updateInstructorRequestDto;
import com.example.package404.instructor.model.dto.res.InstructorCourseListResponseDto;
import com.example.package404.instructor.model.dto.res.InstructorIdDto;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;


    //    //Todo 강사 조회
    public InstructorResponseDto getInstructor(Long instructorIdx) {
        Instructor instructor = instructorRepository.findById(instructorIdx)
                .orElseThrow(() -> new InstructorException(InstructorResponseStatus.INSTRUCTOR_NOT_FOUND));

        return InstructorResponseDto.from(instructor);
    }
// 그냥 서비스로직 내 idx 값 조회
    public Instructor getInstructorId(Long useridx) {
        Optional<Instructor> instructor = instructorRepository.findDistinctInstructorByUserIdx(useridx);
        if (instructor.isPresent()) {
            return InstructorIdDto.from(instructor.get());
        }
        return null;
    }

    public void setinfo(updateInstructorRequestDto dto, User user) {


//        instructorRepository.save(user);
    }




    //    //TOdo 강사 리스트
    //    public List<InstructorResDto> instructor_list() {
    //        instructorRepository.list();
    //    }
}




