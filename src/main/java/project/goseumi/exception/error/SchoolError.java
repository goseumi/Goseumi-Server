package project.goseumi.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum SchoolError implements GoseumiError {
    SCHOOL_FIND_BY_ID_FAIL("01", "아이디에 해당하는 학교의 정보가 존재하지 않습니다.");

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    SchoolError(String errorCode, String message) {
        this.errorCode = "E01" + errorCode; //계정 관련 오류코드 = E01~~
        this.message = message;
    }
}
