package com.example.package404.user.model.Dto;

import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;

public class UserRequestDto {

    @AllArgsConstructor
    @Getter
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
        private String birth;

        public User toEntity(String encodedPassword, Set<String> roles) {
            return User.builder()
                    .email(email)
                    .password(encodedPassword)
                    .name(name)
                    .birth(birth)
                    .roles(roles) // 역할 처리
                    .build();
        }
    }
}


