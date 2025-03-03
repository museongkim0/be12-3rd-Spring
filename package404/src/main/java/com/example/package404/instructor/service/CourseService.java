package com.example.package404.instructor.service;


import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Curriculum;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.res.CourseResponseDto;
import com.example.package404.instructor.model.dto.res.CurriculumResponseDto;
import com.example.package404.instructor.model.dto.res.InstructorCourseListResponseDto;
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
    private final InstructorService instructorService;
    private final CurriculumService curriculumService;

    @Transactional
    public void register(CourseRegister dto, User user) {
        System.out.println(user.getIdx());

        Instructor instructor = instructorService.getInstructorId(user.getIdx());

        Course course = courseRepository.save(dto.toEntity(instructor));
        curriculumService.registerCurriculum(dto.getCurriculumList(), course);



    }


    // 강사의 진행 코스 조회
    public List<InstructorCourseListResponseDto> findIstructorCourse(Long userIdx) {
            List<Course> courses = courseRepository.findByInstructorUserIdx(userIdx);;
            return courses.stream()
                    .map(InstructorCourseListResponseDto::from)  // Course 객체를 CourseListResponseDto로 변환
                    .collect(Collectors.toList());
    }
    // 학원에서 list 조회
    public List<InstructorCourseListResponseDto> list() {
        List<Course> result = courseRepository.findAllCourses();
        return result.stream()
                .map(InstructorCourseListResponseDto::from)  // Course 객체를 CourseListResponseDto로 변환
                .collect(Collectors.toList());  // 변환된 객체들을 리스트로 수집
    }


    public List<CurriculumResponseDto> getCurriculumBySubject(String subject) {
        List<Curriculum> result = curriculumService.getCurriculumBySubject(subject);
        return result.stream().map(CurriculumResponseDto::from).collect(Collectors.toList());


    }


//    public List<CourseResponseDto> list() {
//        List<Course> result = courseRepository.findAllWithAssociations();
//        return result.stream().map(CourseResponseDto::from).collect(Collectors.toList());
//    }


    public List<Course> getCoursesByInstructorId(Long instructorId) {
        return courseRepository.findByInstructorUserIdx(instructorId);
    }




    // 코스 상세 조회
    @Transactional(readOnly = true)
    public CourseResponseDto read(int generation) {
        Course course = courseRepository.findAllWithCurriculumListByGeneration(generation);
        return CourseResponseDto.from(course);
    }


//    @Transactional(readOnly = true)
//    public CourseResponseDto read(Long courseIdx) {
//        Course course = courseRepository.findById(courseIdx).orElseThrow();
//        return CourseResponseDto.from(course);
//    }


    public Course getCourse(Long courseIdx) {
        return courseRepository.findById(courseIdx).orElseThrow();
    }



//    public List<CourseDto.CourseResponse> list(int page, int size) {
//        List<Course> courseList = courseRepository.findAll();
//
//        return courseList.stream().map(CourseDto.CourseResponse::from).collect(Collectors.toList());
//    }

    //기존 list 조회 하지만 밑에  curri 부분 필요 없는것같아서 주석 처리함
//
//    @Transactional(readOnly = true)
//    public List<CourseResponseDto> list() {
//        List<Course> result = courseRepository.findAllWithcurriculumList();
//        return result.stream().map(CourseResponseDto::from).collect(Collectors.toList());
//    }


}
