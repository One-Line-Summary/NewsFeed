package my.newsfeed.exception;

import lombok.Getter;

@Getter

//에러코드 정의
public enum ErrorCode {
    USER_NOT_FOUND("유저를 찾을 수 없습니다."),
    FORBIDDEN_PROFILE("비공개 프로필에 접근할 수 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

}
