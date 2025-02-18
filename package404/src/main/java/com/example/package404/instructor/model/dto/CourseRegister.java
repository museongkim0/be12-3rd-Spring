package com.example.package404.instructor.model.dto;

import com.example.package404.instructor.model.Course;
import com.example.package404.user.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;



@Getter
public class CourseRegister {

    private String name;
    private int generation;
    List<CurriculumRegisterDto> curriculumList = new ArrayList<>();

    public Course toEntity(User user) {
        return Course.builder()
                .name(name)
                .generation(generation)
//                .user(user)
                .build();
    }
}
