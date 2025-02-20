package com.example.package404.response;

import lombok.Getter;

@Getter
public enum ManagerResponseStatus {
    // 5000번대 - Manager 관련 에러
    MANAGER_NOT_FOUND(false, 5001, "매니저 정보를 찾을 수 없습니다."),
    DUPLICATE_MANAGER(false, 5002, "이미 존재하는 매니저입니다."),
    INVALID_MANAGER_ID(false, 5003, "잘못된 매니저 ID입니다."),
    MANAGER_ASSIGNMENT_FAILED(false, 5004, "매니저 배정에 실패했습니다."),
    MANAGER_ACCESS_DENIED(false, 5005, "매니저가 접근할 수 없는 리소스입니다."),
    MANAGER_ROLE_NOT_ALLOWED(false, 5006, "해당 작업을 수행할 권한이 없습니다."),
    MANAGER_CANNOT_MODIFY_COURSE(false, 5007, "매니저가 강의 정보를 수정할 수 없습니다."),
    MANAGER_APPROVAL_REQUIRED(false, 5008, "해당 작업에는 매니저 승인이 필요합니다.");

    private boolean isSuccess; // 성공 여부
    private String message; // 메시지
    private int code; // 코드

    /*
     * BaseResponseStatus 에서 해당하는 코드를 매핑
     *
     * @param isSuccess
     * @param code
     * @param message
     */
    ManagerResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
