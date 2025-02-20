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

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 (Signup)
    public void signup(UserRequestDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        userRepository.save(dto.toEntity(encodedPassword, dto.getroles())); // roles 추가
    }

    // 사용자 정보 로드 (Spring Security)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return user;
    }
}
