package com.example.package404.instructor.model.dto;

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
public class CurriculumResDto {

    private Long idx;
    private String name;

    private LocalDate curriculumDate;
    private int curriculumDay;
    private String curriculumSubject;
    private int curriculumHours;




    public static CurriculumResDto from(Curriculum curriculum) {
        return CurriculumResDto.builder()
                .idx(curriculum.getIdx())
                .name(curriculum.getName())
                .curriculumDate(curriculum.getCurriculumDate())
                .curriculumDay(curriculum.getCurriculumDay())
                .curriculumSubject(curriculum.getCurriculumSubject())
                .curriculumHours(curriculum.getCurriculumHours())
                .build();
    }
}
