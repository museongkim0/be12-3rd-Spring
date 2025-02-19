package com.example.package404.manager.model.dto;

import com.example.package404.manager.model.Manager;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagerResponseDto {
    private Long idx;
    private String email;
    private String password;
    private String nickname;

    public static ManagerResponseDto of(Manager entity) {
        return ManagerResponseDto.builder()
                .idx(entity.getIdx())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .build();
    }
}
