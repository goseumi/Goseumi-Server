package project.goseumi.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SchoolRegisterRequest {

    @NotBlank(message = "학교의 이름은 빈칸일 수 없습니다.")
    private String name;

    @NotNull(message = "학교의 우편번호는 빈칸일 수 없습니다.")
    private Long addressNumber;

    @NotBlank(message = "학교의 도로명 주소는 빈칸일 수 없습니다.")
    private String address;

    private String pageUrl;
}
