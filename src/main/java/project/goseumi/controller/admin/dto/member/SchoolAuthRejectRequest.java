package project.goseumi.controller.admin.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SchoolAuthRejectRequest {

    @NotNull(message = "학교 인증 ID는 비어있을 수 없습니다.")
    private Long id;

    @NotBlank(message = "거절 사유는 비어있을 수 없습니다.")
    private String rejectReason;
}
