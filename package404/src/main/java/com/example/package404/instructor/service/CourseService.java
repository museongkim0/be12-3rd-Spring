package com.example.package404.instructor.service;


import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.res.CourseResponseDto;
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
    private final InstructorService instructorService;


    @Transactional
    public void register(CourseRegister dto, User user) {
        Instructor instructor = instructorService.getInstructorId(user.getIdx());

        Course course = courseRepository.save(dto.toEntity(instructor));

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
    public List<CourseResponseDto> list() {
        List<Course> result = courseRepository.findAll();
        return result.stream().map(CourseResponseDto::from).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CourseResponseDto read(Long courseIdx) {
        Course course = courseRepository.findById(courseIdx).orElseThrow();
        return CourseResponseDto.from(course);
    }


    public Course getCourse(Long courseIdx) {

        return courseRepository.findById(courseIdx).orElseThrow();
    }


}
