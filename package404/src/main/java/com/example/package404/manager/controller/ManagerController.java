package com.example.package404.manager.controller;

import com.example.package404.manager.model.Dto.ManagerRequestDto;
import com.example.package404.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/signup")
    public void signup(@RequestBody ManagerRequestDto dto) {
        managerService.signup(dto);
    }
}
