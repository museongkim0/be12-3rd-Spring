package com.example.package404.manager.controller;

import com.example.package404.instructor.model.Dto.InstructorResponseDto;
import com.example.package404.manager.model.dto.ManagerRequestDto;
import com.example.package404.manager.model.dto.ManagerResponseDto;
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

    @PostMapping("/signup")
    public void signup(@RequestBody ManagerRequestDto dto) {
        managerService.signup(dto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ManagerResponseDto>> list() {
        List<ManagerResponseDto> response = managerService.getList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/manager/{managerIdx}")
    public ResponseEntity<ManagerResponseDto> findManager(@PathVariable Long managerIdx) {
        ManagerResponseDto response = managerService.getManager(managerIdx);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/find/instructor/{instructorIdx}")
    public ResponseEntity<InstructorResponseDto> findInstructor(@PathVariable Long instructorIdx) {
        InstructorResponseDto response = managerService.getInstructor(instructorIdx);
        return ResponseEntity.ok(response);
    }
}
