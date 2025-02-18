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
public class BoardReadResponseDto {
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static BoardReadResponseDto from(Board board) {
        return BoardReadResponseDto.builder().title(board.getTitle()).content(board.getContent()).writer(board.getWriter()).createdDate(board.getCreatedDate()).modifiedDate(board.getModifiedDate()).build();
    }

}
