package com.example.package404.student.model.Dto;

import com.example.package404.student.model.StudentDetail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentRegisterDto {
    private String name;
    private String phoneNumber;
    private String birthDate;

    public StudentDetail toEntity() {
        return StudentDetail.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .build();
    }
}
