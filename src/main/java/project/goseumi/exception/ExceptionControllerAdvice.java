package project.goseumi.exception;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<String> businessExceptionHandler(BusinessException exception) {
        String message = exception.getMessage();
        log.error("BusinessException: {}", message);
        return ResponseEntity.badRequest()
                .body(message);
    }

    @ExceptionHandler(value = AuthenticationCustomException.class)
    protected ResponseEntity<String> authenticationCustomExceptionHandler(AuthenticationCustomException exception) {
        String message = exception.getMessage();
        log.error("AuthenticationCustomException: {}", message);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();
        PropertyNamingStrategies.SnakeCaseStrategy snakeCaseStrategy = new PropertyNamingStrategies.SnakeCaseStrategy();

        exception.getBindingResult().getAllErrors()
                .forEach(c -> {
                    String fieldName = ((FieldError) c).getField();
                    String snakeCaseFieldName = snakeCaseStrategy.translate(fieldName);
                    errors.put(snakeCaseFieldName, c.getDefaultMessage());
                });
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", errors);
        response.put("path", request.getRequestURI());

        log.error("MethodArgumentNotValidException: {}", errors);

        return ResponseEntity.badRequest()
                .body(response);
    }
}
