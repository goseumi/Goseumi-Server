package project.goseumi.exception.handler.response;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import project.goseumi.exception.BusinessException;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDto {
    private final String errorCode;
    private final String message;
    private final String uri;
    private final LocalDateTime timeStamp;

    @Builder
    protected ErrorResponseDto(String errorCode, String message, String uri, LocalDateTime timeStamp) {
        this.errorCode = errorCode;
        this.message = message;
        this.uri = uri;
        this.timeStamp = timeStamp;
    }

    public static ErrorResponseDto of(BusinessException exception, HttpServletRequest request) {
        return ErrorResponseDto.builder()
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .uri(request.getRequestURI())
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
