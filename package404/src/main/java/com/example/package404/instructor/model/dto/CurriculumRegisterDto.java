package com.example.package404.instructor.model.dto;

import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Curriculum;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class CurriculumRegisterDto {


    private LocalDate curriculumDate;
    private int curriculumDay;
    private String curriculumSubject;
    private int curriculumHours;



    public Curriculum toEntity(Course course) {
        return Curriculum.builder()
                .curriculumDate(curriculumDate)
                .curriculumDay(curriculumDay)
                .curriculumSubject(curriculumSubject)
                .curriculumHours(curriculumHours)
                .course(course)
                .build();
    }
}
