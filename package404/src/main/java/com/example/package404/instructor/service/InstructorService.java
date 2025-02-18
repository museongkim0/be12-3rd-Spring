package com.example.package404.instructor.service;


import com.example.package404.instructor.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;


//    //Todo 강사 조회
//    public InstructorResDto getInstructorById(Long instructorId) {
////        instructorRepository
//    }

//    //TOdo 강사 리스트
//    public List<InstructorResDto> instructor_list() {
//        instructorRepository.list();
//    }

}
