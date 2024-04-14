package project.goseumi.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.goseumi.exception.BusinessException;
import project.goseumi.exception.handler.response.ErrorResponseDto;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<ErrorResponseDto> businessExceptionHandler(
            BusinessException exception, HttpServletRequest request
    ) {
        log.error("business error => " + exception);
        return ResponseEntity.status(exception.getHttpStatus())
                .body(ErrorResponseDto.of(exception, request));
    }
}
