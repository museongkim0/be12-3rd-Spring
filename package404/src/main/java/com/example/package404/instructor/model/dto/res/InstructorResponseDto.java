package com.example.package404.instructor.model.dto.res;

import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorResponseDto {

    private Long userIdx;  // user의 PK
    private String record;
    private String portfolio;
    private String email;  // 유저 정보 추가
    private String name;

    public static InstructorResponseDto from(Instructor instructor) {

        return InstructorResponseDto.builder()
                .userIdx(instructor.getUserIdx())
                .record(instructor.getRecord())
                .portfolio(instructor.getPortfolio())
                .email(instructor.getUser().getEmail())
                .name(instructor.getUser().getName())
                .build();

    }

}