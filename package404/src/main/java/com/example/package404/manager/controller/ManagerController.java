package com.example.package404.manager.controller;

import com.example.package404.instructor.model.dto.InstructorResponseDto;
import com.example.package404.manager.model.dto.ManagerResponseDto;
import com.example.package404.manager.model.dto.TestRequestDto;
import com.example.package404.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/list")
    public ResponseEntity<List<ManagerResponseDto>> list() {
        List<ManagerResponseDto> response = managerService.getList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{managerIdx}")
    public ResponseEntity<ManagerResponseDto> findManager(@PathVariable Long managerIdx) {
        ManagerResponseDto response = managerService.getManager(managerIdx);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/instructor/{instructorIdx}")
    public ResponseEntity<InstructorResponseDto> findInstructor(@PathVariable Long instructorIdx) {
        InstructorResponseDto response = managerService.getInstructor(instructorIdx);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/test/register")
    public void registerTest(@RequestBody TestRequestDto dto) {
        managerService.registerTest(dto);
    }

    @PostMapping("/test/update/{testIdx}")
    public void updateTest(Long testIdx, @RequestBody TestRequestDto dto) {
        managerService.updateTest(testIdx, dto);
    }

    @PostMapping("/test/delete/{testIdx}")
    public void deleteTest(Long testIdx) {
        managerService.deleteTest(testIdx);
    }
}
