package com.example.package404.instructor.model.dto.res;

import com.example.package404.instructor.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResDto {

    private Long idx;
    private String name;
    private int generation;


    List<CurriculumResDto> curriculumList = new ArrayList<>();

    public static CourseResDto from(Course course) {
        return CourseResDto.builder()
                .idx(course.getIdx())
                .name(course.getName())
                .generation(course.getGeneration())
                .curriculumList(course.getSectionList().stream().map(CurriculumResDto::from).collect(Collectors.toList()))
                .build();
    }

}
