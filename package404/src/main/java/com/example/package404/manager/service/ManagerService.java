package com.example.package404.manager.service;

import com.example.package404.instructor.model.Dto.InstructorResponseDto;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.instructor.service.InstructorService;
import com.example.package404.manager.model.Dto.ManagerRequestDto;
import com.example.package404.manager.model.Dto.ManagerResponseDto;
import com.example.package404.manager.model.Manager;
import com.example.package404.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    //private final InstructorService instructorService;
    //private final PasswordEncoder passwordEncoder;

    public void signup(ManagerRequestDto dto) {
        //String encodedPassword = passwordEncoder.encode(dto.getPassword());
        managerRepository.save(dto.toEntity(dto.getPassword()/*encodedPassword*/));
    }

    public List<ManagerResponseDto> getList() {
        List<Manager> managerList = managerRepository.findAll();

        return managerList.stream().map(ManagerResponseDto::of).toList();
    }

    public ManagerResponseDto getManager(Long managerIdx) {
        Manager manager = managerRepository.findById(managerIdx).orElseThrow();

        return ManagerResponseDto.of(manager);
    }

    public InstructorResponseDto getInstructor(Long instructorIdx) {
        return instructorService.getInstructor(instructorIdx);
    }
}
