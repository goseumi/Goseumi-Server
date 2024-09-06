package project.goseumi.controller.dto.board;

import lombok.Builder;
import lombok.Getter;
import project.goseumi.domain.Board;

@Getter
public class UpdateBoardRequest {

    private Long boardId;
    private String title;
    private String content;

    protected UpdateBoardRequest() {

    }

    @Builder
    public UpdateBoardRequest(Long boardId, String title, String content) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
    }

    public static UpdateBoardRequest of(Board board) {
        return UpdateBoardRequest.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
