package project.goseumi.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberError implements GoseumiError {

    USER_LOGIN_FAIL("01", "회원 인증에 실패했습니다."),
    EMAIL_DUPLICATE("02", "중복된 이메일입니다."),
    NICKNAME_DUPLICATE("03", "중복된 닉네임입니다.");

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED; //401 인증에러 유효하지 않은 사용자 정보

    MemberError(String errorCode, String message) {
        this.errorCode = "E00" + errorCode; //계정 관련 오류코드 = E00~~
        this.message = message;
    }
}
