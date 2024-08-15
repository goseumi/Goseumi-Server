package project.goseumi.controller.dto.response;

import lombok.Getter;

@Getter
public class SuccessResponseDto {
    private boolean success;

    // 성공 여부를 담아서 객체를 만들어 반환하기 위해 생성자 추가
    public SuccessResponseDto(boolean success) {
        this.success = success;
    }
}
