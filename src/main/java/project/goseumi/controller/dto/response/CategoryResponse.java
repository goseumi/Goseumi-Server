package project.goseumi.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import project.goseumi.domain.BoardCategory;
import project.goseumi.domain.value.VisibleState;

@Getter
public class CategoryResponse {

    private final Long id;
    private final String name;
    private final VisibleState state;

    @Builder
    protected CategoryResponse(Long id, String name, VisibleState state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public static CategoryResponse of(BoardCategory category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .state(category.getState())
                .build();
    }
}
