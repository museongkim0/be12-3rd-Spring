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
import jakarta.transaction.Transactional;
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



    //Todo 어떻게 만들어야 할지 잘 모르곘어서 놔둠
    //Todo User랑 instructor 랑 같이 수정할지 user 랑 instructor 따로 수정할지 잘 모르곘음 .
    // 강사 정보 수정
    @Transactional
    public void setInfo(Long instructorIdx, InstructorRequestDto dto, User user) {
        Instructor instructor = instructorRepository.findInstructorWithUser(instructorIdx)
                .orElseThrow(() -> new InstructorException(InstructorResponseStatus.INSTRUCTOR_NOT_FOUND));

        // 사용자가 해당 강사의 정보 수정 권한이 있는지 확인
        if (!instructor.getUser().getIdx().equals(user.getIdx())) {
            throw new InstructorException(InstructorResponseStatus.INVALID_INSTRUCTOR_ID);
        }
        //        instructor.updateInfo(dto.toEntity(instructor));
    }


        // 강사 리스트 조회
        public List<InstructorResponseDto> instructor_list() {
           List<Instructor> responseDtoList = instructorRepository.findAll();
           return responseDtoList.stream().map(InstructorResponseDto::from).collect(Collectors.toList());
        }
}




