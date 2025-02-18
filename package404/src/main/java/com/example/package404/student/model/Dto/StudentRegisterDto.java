package com.example.package404.student.model.Dto;

import com.example.package404.student.model.Student;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentRegisterDto {
    private String name;
    private String phoneNumber;
    private String birthDate;

    public Student toEntity() {
        return Student.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .build();
    }
}
