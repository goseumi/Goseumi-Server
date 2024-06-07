package project.goseumi.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SchoolAuthRequest {

    @NotNull(message = "학교 ID는 비어있을 수 없습니다.")
    private Long schoolId;

    @NotBlank(message = "학생증 이미지는 비어있을 수 없습니다.")
    private String imgUrl;
}
