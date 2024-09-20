package project.goseumi.controller.dto.board;

import lombok.Builder;
import lombok.Getter;
import project.goseumi.domain.Board;

import java.time.LocalDateTime;

@Getter
public class GetBoardResponse {

    private final LocalDateTime createdAt;
    private final String title;
    private final String content;

    @Builder
    public GetBoardResponse(LocalDateTime createdAt, String title, String content) {
        this.createdAt = createdAt;
        this.title = title;
        this.content = content;
    }

    public static GetBoardResponse of(Board board) {
        return GetBoardResponse.builder()
                .createdAt(board.getCreatedAt())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
