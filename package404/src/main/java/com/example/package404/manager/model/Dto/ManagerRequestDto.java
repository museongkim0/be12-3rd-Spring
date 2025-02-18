package com.example.package404.manager.model.Dto;

import com.example.package404.manager.model.Manager;

public class ManagerRequestDto {
    private String email;
    private String password;
    private String nickName;

    public Manager toEntity(String encodedPassword) {
        return Manager.builder()
                .email(email)
                .nickname(nickName)
                .password(encodedPassword)
                .build();
    }
}
