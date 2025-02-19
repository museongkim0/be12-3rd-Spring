package com.example.package404.manager.service;

import com.example.package404.instructor.model.dto.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.instructor.service.InstructorService;
import com.example.package404.manager.model.dto.ManagerResponseDto;
import com.example.package404.manager.repository.ManagerRepository;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final InstructorRepository instructorRepository;
    //private final PasswordEncoder passwordEncoder;

    public List<ManagerResponseDto> getList() {
        List<User> managerList = managerRepository.findAll();

        return managerList.stream().map(ManagerResponseDto::of).toList();
    }

    public ManagerResponseDto getManager(Long managerIdx) {
        User manager = managerRepository.findById(managerIdx).orElseThrow();

        return ManagerResponseDto.of(manager);
    }

    public InstructorResponseDto getInstructor(Long instructorIdx) {
        //return instructorService.getInstructor(instructorIdx);
        User instructor =  instructorRepository.findById(instructorIdx).orElseThrow();
        return InstructorResponseDto.of(instructor);
    }
}
