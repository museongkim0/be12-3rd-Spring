package com.example.package404.board.model.Dto;

import com.example.package404.board.model.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
    private String writer;

    public Board toEntity(int boardType) {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .createdDate(LocalDateTime.now())  // 현재 시간 설정
                .modifiedDate(null)  // 수정 시간은 null
                .boardType(boardType)
                .build();
    }
}
