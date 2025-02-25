package com.example.package404.manager.service;

import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.manager.model.Test;
import com.example.package404.manager.model.dto.ManagerResponseDto;
import com.example.package404.manager.model.dto.TestRequestDto;
import com.example.package404.manager.model.dto.TestResponseDto;
import com.example.package404.manager.repository.ManagerRepository;
import com.example.package404.manager.repository.TestRepository;
import com.example.package404.student.model.Dto.StudentDetailResponseDto;
import com.example.package404.student.model.Dto.StudentResponseDto;
import com.example.package404.student.model.StudentDetail;
import com.example.package404.student.repository.StudentRepository;
import com.example.package404.user.model.Dto.UserResponseDto;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final ManagerRepository managerRepository;
    private final TestRepository testRepository;

    public List<ManagerResponseDto> getManagerList() {
        List<User> managerList = managerRepository.findAll();

        return managerList.stream().map(ManagerResponseDto::of).toList();
    }

    public ManagerResponseDto getManager(Long managerIdx) {
        User manager = managerRepository.findById(managerIdx).orElseThrow();

        return ManagerResponseDto.of(manager);
    }

    public UserResponseDto.SignupResponse getUser(Long userIdx) {
        User user = userRepository.findById(userIdx).orElseThrow();
        return UserResponseDto.SignupResponse.from(user);
    }

    public List<InstructorResponseDto> getInstructorList() {
        List<Instructor> instructorList = instructorRepository.findAll();

        return instructorList.stream().map(InstructorResponseDto::from).toList();
    }

    public List<StudentDetailResponseDto> getStudentList() {
        List<StudentDetail> studentList = studentRepository.findAll();

        return studentList.stream().map(StudentDetailResponseDto::from).toList();
    }

    public TestResponseDto registerTest(TestRequestDto dto){
        Test test = testRepository.save(dto.toEntity());
        return TestResponseDto.of(test);
    }

    public TestResponseDto updateTest(Long testIdx, TestRequestDto dto) {
        Test test = testRepository.findById(testIdx)
                .orElseThrow(() -> new RuntimeException("Test with id " + testIdx + " not found"));

        test.setTitle(dto.getTitle());
        test.setContent(dto.getContent());

        testRepository.save(test);

        return TestResponseDto.of(test);
    }

    public TestResponseDto deleteTest(Long testIdx) {
        Test test = testRepository.findById(testIdx)
                .orElseThrow(() -> new RuntimeException("Test with id " + testIdx + " not found"));

        testRepository.delete(test);

        return TestResponseDto.of(test);
    }

    public List<TestResponseDto> getTestList() {
        List<Test> testList = testRepository.findAllWithCourse();

        return testList.stream().map(TestResponseDto::of).toList();
    }

}
