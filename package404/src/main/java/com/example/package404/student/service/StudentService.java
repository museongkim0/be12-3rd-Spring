package com.example.package404.student.service;

import com.example.package404.student.model.Dto.StudentRegisterDto;
import com.example.package404.student.model.Dto.StudentResponseDto;
import com.example.package404.student.model.StudentDetail;
import com.example.package404.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public void register(StudentRegisterDto dto){
        studentRepository.save(dto.toEntity());
    }

    public List<StudentResponseDto> list() {
        List<StudentDetail> boardList = studentRepository.findAll();
        return boardList.stream().map(StudentResponseDto::from).collect(Collectors.toList());
    }

    public StudentResponseDto read(Long idx) {
        StudentDetail studentDetail = studentRepository.findById(idx).orElseThrow();

        return StudentResponseDto.from(studentDetail);
    }
}
