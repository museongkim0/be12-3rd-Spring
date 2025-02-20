package com.example.package404.instructor.model.dto.req;

import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Curriculum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
