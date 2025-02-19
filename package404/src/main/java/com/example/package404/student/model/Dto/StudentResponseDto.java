package com.example.package404.student.model.Dto;

import com.example.package404.student.model.StudentDetail;
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

    public static StudentResponseDto from(StudentDetail studentDetail) {
        return StudentResponseDto.builder()
                .idx(studentDetail.getIdx())
                .name(studentDetail.getName())
                .phoneNumber(studentDetail.getPhoneNumber())
                .birthDate(studentDetail.getBirthDate())
                .build();
    }
}
