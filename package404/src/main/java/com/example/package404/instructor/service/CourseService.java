package com.example.package404.instructor.service;


import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.dto.CourseRegister;
import com.example.package404.instructor.model.dto.CourseResDto;
import com.example.package404.instructor.repository.CourseRepository;
import com.example.package404.instructor.repository.CurriculumRepository;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CurriculumRepository curriculumRepository;


    @Transactional
    public void register(CourseRegister dto, User user) {
        Course course = courseRepository.save(dto.toEntity(user));

        dto.getCurriculumList().forEach(Course_CurriculumRegisterDto -> {
            curriculumRepository.save(Course_CurriculumRegisterDto.toEntity(course));
        });
    }

//    public List<CourseDto.CourseResponse> list(int page, int size) {
//        List<Course> courseList = courseRepository.findAll();
//
//        return courseList.stream().map(CourseDto.CourseResponse::from).collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public List<CourseResDto> list() {
        List <Course> result = courseRepository.findAll();
        return result.stream().map(CourseResDto::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseResDto read(Long courseIdx) {
        Course course = courseRepository.findById(courseIdx).orElseThrow();
        return CourseResDto.from(course);
    }





}
