package com.example.package404.user.model.Dto;

import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class UserRequestDto {

    @AllArgsConstructor
    @Getter
    public static class SignupRequest {
        private String email;
        private String password;

        public User toEntity(String encodedPassword, List<String> roles) {
            return User.builder()
                    .email(email)
                    .password(encodedPassword)
                    .roles(roles) // 여러 개의 권한을 저장할 수 있도록 수정
                    .build();
        }

    }


}
