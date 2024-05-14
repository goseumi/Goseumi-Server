package project.goseumi.controller.dto.response;

import lombok.Getter;

@Getter
public class CreateCategoryResponseDto {
    private final String name;

    public CreateCategoryResponseDto(String name) {
        this.name = name;
    }
}
