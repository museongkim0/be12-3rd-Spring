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
import java.util.Optional;
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
//        List<User> studentList = userRepository.findByRoleEquals("ROLE_STUDENT");
        List<StudentDetail> studentList = studentRepository.findAllStudents();
        return studentList.stream().map(StudentResponseDto::from).collect(Collectors.toList());
    }

    public StudentResponseDto read(Long idx) {
        StudentDetail student = studentRepository.findByStudent(idx).orElseThrow();

        return StudentResponseDto.from(student);
    }

    public void update(User user, String action) {
        User findUser = userRepository.findById(user.getIdx()).orElseThrow();
        StudentDetail studentDetail = studentRepository.findById(findUser.getStudentDetail().getIdx()).orElseThrow();

        if (action.equals("testStatus")) {
            studentDetail.updateTestStatus();
            studentRepository.save(studentDetail);
        } else if (action.equals("perception")) {
            studentDetail.updatePerception();
            studentRepository.save(studentDetail);
        } else if (action.equals("attendance")) {
            studentDetail.updateAttendance();
            studentRepository.save(studentDetail);
        } else if (action.equals("leaveEarly")) {
            studentDetail.updateLeaveEarly();
            studentRepository.save(studentDetail);
        } else if (action.equals("outing")) {
            studentDetail.updateOuting();
            studentRepository.save(studentDetail);
        } else if (action.equals("vacationLeft")) {
            studentDetail.updateVacationLeft();
            studentRepository.save(studentDetail);
        }
    }
}
