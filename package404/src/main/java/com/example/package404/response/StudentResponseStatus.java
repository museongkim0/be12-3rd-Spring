package com.example.package404.response;

import lombok.Getter;

@Getter
public enum StudentResponseStatus {

    // 3000번대 - Student 관련 에러
    STUDENT_NOT_FOUND(false, 3001, "학생 정보를 찾을 수 없습니다."),
    DUPLICATE_STUDENT(false, 3002, "이미 존재하는 학생입니다."),
    INVALID_STUDENT_ID(false, 3003, "잘못된 학생 ID입니다."),
    STUDENT_ENROLLMENT_FAILED(false, 3004, "학생 등록에 실패했습니다."),
    STUDENT_ALREADY_ENROLLED(false, 3005, "이미 해당 강의에 등록된 학생입니다."),
    COURSE_LIMIT_EXCEEDED(false, 3006, "학생이 신청할 수 있는 최대 강의 수를 초과했습니다."),
    STUDENT_ACCESS_DENIED(false, 3007, "학생이 접근할 수 없는 리소스입니다."),
    STUDENT_PROGRESS_NOT_FOUND(false, 3008, "학생의 학습 진행 정보를 찾을 수 없습니다."),
    STUDENT_GRADE_NOT_FOUND(false, 3009, "학생의 성적 정보를 찾을 수 없습니다.");

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
    StudentResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
