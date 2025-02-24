package com.example.package404.user.model.Dto;


import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

public class UserRequestDto {

    @AllArgsConstructor
    @Getter
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
        private LocalDate birth;
        private String role;

        public User toEntity(String encodedPassword, String instructor) {
            return User.builder()
                    .email(email)
                    .password(encodedPassword)
                    .name(name)
                    .birth(birth)
                    .role(role)
                    .build();
        }
    }
}
