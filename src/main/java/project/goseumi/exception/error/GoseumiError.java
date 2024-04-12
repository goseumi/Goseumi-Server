package project.goseumi.exception.error;

import org.springframework.http.HttpStatus;

public interface GoseumiError {

    String getErrorCode();

    String getMessage();

    HttpStatus getHttpStatus();
}
