package com.example.package404.student.model.Dto;

import com.example.package404.student.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDto {
    private Long idx;
    private String name;
    private String phoneNumber;
    private String birthDate;

    public static StudentResponseDto from(Student student) {
        return StudentResponseDto.builder()
                .idx(student.getIdx())
                .name(student.getName())
                .phoneNumber(student.getPhoneNumber())
                .birthDate(student.getBirthDate())
                .build();
    }
}
