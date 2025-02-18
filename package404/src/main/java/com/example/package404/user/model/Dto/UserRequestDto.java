package com.example.package404.user.model.Dto;

import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserRequestDto {

    @AllArgsConstructor
    @Getter
    public static class SignupRequest {
        private String email;
        private String password;

        public User toEntity(String encodedPassword) {
            return User.builder()
                    .email(email)
                    .password(encodedPassword)
                    .role("ROLE_USER")
                    .build();
        }
    }


}
