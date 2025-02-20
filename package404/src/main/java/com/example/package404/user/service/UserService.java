package com.example.package404.user.service;

import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import com.example.package404.user.model.Dto.UserRequestDto;
import com.example.package404.user.model.Dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserRequestDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = userRepository.save(dto.toEntity(encodedPassword, "USER"));
    }

    @Transactional
    public void instructorSignup(UserRequestDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        userRepository.save(dto.toEntity(encodedPassword, "INSTRUCTOR"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(username);

        if (result.isPresent()) {
            // 7번 로직
            User user = result.get();
            return user;
        }

        return null;
    }

}

