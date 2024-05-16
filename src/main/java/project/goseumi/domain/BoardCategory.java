package project.goseumi.domain;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import project.goseumi.controller.dto.request.CreateCategoryRequestDto;

@Entity
@Getter
public class BoardCategory extends BaseEntity {

    private String name;

    protected BoardCategory() {

    }

    @Builder
    protected BoardCategory(String name) {
        this.name = name;
    }

    public static BoardCategory of(CreateCategoryRequestDto registerBoardCategoryRequestDto) {
        return BoardCategory.builder()
                .name(registerBoardCategoryRequestDto.getName())
                .build();
    }

    public void rename(String rename) {
        this.name = rename;
    }
}