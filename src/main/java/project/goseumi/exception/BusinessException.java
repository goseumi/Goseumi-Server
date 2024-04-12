package project.goseumi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import project.goseumi.exception.error.GoseumiError;

@Getter
public class BusinessException extends RuntimeException {

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    public BusinessException(GoseumiError goseumiError) {
        super(goseumiError.getMessage());
        this.errorCode = goseumiError.getErrorCode();
        this.message = goseumiError.getMessage();
        this.httpStatus = goseumiError.getHttpStatus();
    }

}
