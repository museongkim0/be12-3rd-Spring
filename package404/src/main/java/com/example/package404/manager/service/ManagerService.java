package com.example.package404.manager.service;

import com.example.package404.instructor.model.dto.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.instructor.service.InstructorService;
import com.example.package404.manager.model.Test;
import com.example.package404.manager.model.dto.ManagerResponseDto;
import com.example.package404.manager.model.dto.TestRequestDto;
import com.example.package404.manager.repository.ManagerRepository;
import com.example.package404.manager.repository.TestRepository;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final TestRepository testRepository;
    private final InstructorRepository instructorRepository;

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

    public void registerTest(TestRequestDto dto){
        testRepository.save(dto.toEntity());
    }

    public void updateTest(Long testIdx, TestRequestDto dto) {
        Test test = testRepository.findById(testIdx)
                .orElseThrow(() -> new RuntimeException("Test with id " + testIdx + " not found"));

        test.setTitle(dto.getTitle());
        test.setContent(dto.getContent());

        testRepository.save(test);
    }

    public void deleteTest(Long testIdx) {
        if (!testRepository.existsById(testIdx)) {
            throw new RuntimeException("Test with id " + testIdx + " not found");
        }
        testRepository.deleteById(testIdx);
    }
}
