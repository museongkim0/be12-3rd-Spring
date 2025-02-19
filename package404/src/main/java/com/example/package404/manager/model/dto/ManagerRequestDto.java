package com.example.package404.manager.model.dto;

import com.example.package404.manager.model.Manager;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagerRequestDto {
    private String email;
    private String password;
    private String nickname;

    public Manager toEntity(String encodedPassword) {
        return Manager.builder()
                .email(email)
                .nickname(nickname)
                .password(encodedPassword)
                .build();
    }
}
