package com.example.package404.instructor.service;


import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.req.InstructorRequestDto;
import com.example.package404.instructor.model.dto.res.InstructorIdDto;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

//    //Todo 강사 조회
    public InstructorResponseDto getInstructor(Long instructorIdx) {

        Optional<Instructor> instructor = instructorRepository.findById(instructorIdx);

        if(instructor.isPresent()) {
            return InstructorResponseDto.from(instructor.get());
        }

        return null;
    }
//    public Instructor getInstructorId(Long useridx){
//        Optional<Instructor> instructor = instructorRepository.findById(useridx);
//        if(instructor.isPresent()) {
//            return InstructorIdDto.from(instructor.get());
//        }
//        return null;
//    }

    public void setinfo(InstructorRequestDto dto, User user) {
        instructorRepository.save(dto.toEntity(user));
    }

//    //TOdo 강사 리스트
//    public List<InstructorResDto> instructor_list() {
//        instructorRepository.list();
//    }

}
