package project.goseumi.controller.admin.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCategoryRequestDto {

    @NotBlank(message = "게시판의 카테고리는 빈칸일 수 없습니다.")
    private String name;
}
