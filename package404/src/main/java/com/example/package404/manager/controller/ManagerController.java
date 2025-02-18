package com.example.package404.manager.controller;

import com.example.package404.manager.model.Dto.ManagerRequestDto;
import com.example.package404.manager.model.Dto.ManagerResponseDto;
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

    @GetMapping("/find/{managerIdx}")
    public ResponseEntity<ManagerResponseDto> find(@PathVariable Long managerIdx) {
        ManagerResponseDto response = managerService.get(managerIdx);
        return ResponseEntity.ok(response);
    }
}
