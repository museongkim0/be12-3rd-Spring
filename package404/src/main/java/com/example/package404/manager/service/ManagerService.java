package com.example.package404.manager.service;

import com.example.package404.manager.model.Dto.ManagerRequestDto;
import com.example.package404.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(ManagerRequestDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        managerRepository.save(dto.toEntity(encodedPassword));
    }
}
