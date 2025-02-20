package com.example.package404.instructor.model.dto.res;

import com.example.package404.instructor.model.Instructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorResDto {


    private Long idx;
    private String record;

    private String portfolio;


    public static InstructorResDto from(Instructor instructor) {

        return InstructorResDto.builder()
                .idx(instructor.getIdx())
                .record(instructor.getRecord())
                .portfolio(instructor.getPortfolio())
                .build();

    }

}