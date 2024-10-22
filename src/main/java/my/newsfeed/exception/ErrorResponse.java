package my.newsfeed.exception;


import lombok.Getter;

@Getter

//에러 응답 객체
public class ErrorResponse {
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(String message) {
        this.message = message;
    }
}
