package project.goseumi.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SchoolAuthError implements GoseumiError {
    SCHOOL_AUTH_FIND_BY_ID_FAIL("01", "아이디에 해당하는 학교인증 정보가 존재하지 않습니다.");

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    SchoolAuthError(String errorCode, String message) {
        this.errorCode = "E02" + errorCode; //학교인증 관련 오류코드 = E02~~
        this.message = message;
    }
}
