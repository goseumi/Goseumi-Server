package project.goseumi.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SchoolAuthAccessRequest {

    @NotNull(message = "학교 인증 ID는 비어있을 수 없습니다.")
    private Long id;
}
