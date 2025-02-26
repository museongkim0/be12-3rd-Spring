package com.example.package404.manager.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.manager.model.dto.ManagerResponseDto;
import com.example.package404.manager.model.dto.TestRequestDto;
import com.example.package404.manager.model.dto.TestResponseDto;
import com.example.package404.manager.service.ManagerService;
import com.example.package404.user.model.Dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;
    private final BaseResponseServiceImpl baseResponseService;

    @GetMapping("/list")
    public BaseResponse<Object> managerList() {
        List<ManagerResponseDto> response = managerService.getManagerList();

        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @GetMapping("/{managerIdx}")
    public BaseResponse<Object> findManager(@PathVariable Long managerIdx) {
        ManagerResponseDto response = managerService.getManager(managerIdx);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @GetMapping("/instructor/list")
    public BaseResponse<Object> instructorList() {
        List<InstructorResponseDto> response = managerService.getInstructorList();

        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @PostMapping("/test/register")
    public BaseResponse<Object> registerTest(@RequestBody TestRequestDto dto) {
        TestResponseDto response = managerService.registerTest(dto);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @PostMapping("/test/update/{testIdx}")
    public BaseResponse<Object> updateTest(Long testIdx, @RequestBody TestRequestDto dto) {
        TestResponseDto response = managerService.updateTest(testIdx, dto);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @PostMapping("/test/delete/{testIdx}")
    public BaseResponse<Object> deleteTest(Long testIdx) {
        TestResponseDto response = managerService.deleteTest(testIdx);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @GetMapping("/test/list")
    public BaseResponse<Object> testList() {
        List<TestResponseDto> response = managerService.getTestList();

        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }
}
