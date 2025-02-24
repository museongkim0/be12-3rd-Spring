package com.example.package404.comment.model.dto;

import com.example.package404.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    private String content;
    private String writer;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .writer(writer)
                .createdDate(LocalDateTime.now())
                .modifiedDate(null).build();
    }

}
