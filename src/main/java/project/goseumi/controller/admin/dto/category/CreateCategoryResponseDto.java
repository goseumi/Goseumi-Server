package project.goseumi.controller.admin.dto.category;

import lombok.Getter;

@Getter
public class CreateCategoryResponseDto {
    private final String name;

    public CreateCategoryResponseDto(String name) {
        this.name = name;
    }
}
