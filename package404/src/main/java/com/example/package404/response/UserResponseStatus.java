package com.example.package404.response;

import lombok.Getter;

@Getter
public enum UserResponseStatus {
    // 2000번대 - User 관련 에러
    USER_NOT_FOUND(false, 2001, "사용자를 찾을 수 없습니다."),
    DUPLICATE_USER(false, 2002, "이미 존재하는 사용자입니다."),
    INVALID_USER_ID(false, 2003, "잘못된 사용자 ID입니다."),
    UNAUTHORIZED_USER(false, 2004, "인증되지 않은 사용자입니다."),
    FORBIDDEN_USER(false, 2005, "접근 권한이 없습니다."),
    INVALID_LOGIN_CREDENTIALS(false, 2006, "아이디 또는 비밀번호가 올바르지 않습니다."),
    ACCOUNT_LOCKED(false, 2007, "계정이 잠겼습니다."),
    ACCOUNT_EXPIRED(false, 2008, "계정이 만료되었습니다."),
    PASSWORD_EXPIRED(false, 2009, "비밀번호가 만료되었습니다."),
    INVALID_PASSWORD(false, 2010, "잘못된 비밀번호입니다."),
    TOO_MANY_FAILED_ATTEMPTS(false, 2011, "로그인 시도 횟수가 초과되었습니다."),
    EMAIL_ALREADY_IN_USE(false, 2012, "해당 이메일은 이미 사용 중입니다."),
    PHONE_NUMBER_ALREADY_IN_USE(false, 2013, "해당 전화번호는 이미 사용 중입니다.");

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
    UserResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

}
