package project.goseumi.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FieldError implements GoseumiError {
    INVALID_PAGE("01", "유효하지 않은 페이지입니다.");

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    FieldError(String errorCode, String message) {
        this.errorCode = "E06" + errorCode; //필드 관련 오류코드 = E06~~
        this.message = message;
    }
}
