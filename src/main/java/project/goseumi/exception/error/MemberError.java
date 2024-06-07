package project.goseumi.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberError implements GoseumiError {

    USER_LOGIN_FAIL("01", "회원 인증에 실패했습니다."),
    EMAIL_DUPLICATE("02", "중복된 이메일입니다."),
    NICKNAME_DUPLICATE("03", "중복된 닉네임입니다."),
    PASSWORD_MISMATCH("04", "비밀번호 확인이 일치하지 않습니다."),
    NOT_EXIST_TOKEN("05", "헤더에 토큰이 존재하지 않거나, 유효한 토큰이 아닙니다.");


    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED; //401 인증에러 유효하지 않은 사용자 정보

    MemberError(String errorCode, String message) {
        this.errorCode = "E00" + errorCode; //계정 관련 오류코드 = E00~~
        this.message = message;
    }
}
