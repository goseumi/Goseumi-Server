package project.goseumi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import project.goseumi.controller.admin.dto.category.CreateCategoryRequestDto;
import project.goseumi.domain.value.VisibleState;

@Entity
@Getter
public class BoardCategory extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private VisibleState state;

    protected BoardCategory() {
        state = VisibleState.VISIBLE;
    }

    @Builder
    protected BoardCategory(String name) {
        this();
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

    public void visibleDeleted() {
        this.state = VisibleState.DELETE;
    }

    public void visibleActive() {
        this.state = VisibleState.VISIBLE;
    }
}