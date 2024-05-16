package project.goseumi.exception.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BoardCategoryError implements GoseumiError {
    NOT_FOUND_CATEGORY_BY_ID("01", "{id}에 해당하는 값을 찾을 수 없습니다.");

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    BoardCategoryError(String errorCode, String message) {
        this.errorCode = "E05" + errorCode; //카테고리 관련 오류코드 = E05~~
        this.message = message;
    }
}
