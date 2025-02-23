package com.example.package404.student.service;

import com.example.package404.student.model.Dto.StudentDetailRegisterDto;
import com.example.package404.student.model.Dto.StudentDetailUpdateDto;
import com.example.package404.student.model.Dto.StudentResponseDto;
import com.example.package404.student.model.StudentDetail;
import com.example.package404.student.repository.StudentRepository;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public void register(StudentDetailRegisterDto dto, User user){
        studentRepository.save(dto.toEntity(user));
    }

    public List<StudentResponseDto> list() {
        List<User> studentList = userRepository.findByRoleEquals("ROLE_STUDENT");
        return studentList.stream().map(StudentResponseDto::from).collect(Collectors.toList());
    }

    public StudentResponseDto read(Long idx) {
        User user = userRepository.findById(idx).orElseThrow();

        return StudentResponseDto.from(user);
    }

    public void update(User user, String action) {
        StudentDetail studentDetail = studentRepository.findByUserIdx(user);

        studentRepository.save(StudentDetailUpdateDto.toEntity(studentDetail, action));
    }
}
