package com.example.package404.user.model.Dto;

import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserRequestDto {

    @AllArgsConstructor
    @Getter
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
        private LocalDate birth;
        private String role;

        public User toEntity(String encodedPassword, String role) {
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


