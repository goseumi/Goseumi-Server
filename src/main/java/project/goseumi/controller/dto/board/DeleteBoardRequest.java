package project.goseumi.controller.dto.board;

import lombok.Builder;
import lombok.Getter;
import project.goseumi.domain.Board;

@Getter
public class DeleteBoardRequest {

    private Long boardId;

    protected DeleteBoardRequest() {

    }

    @Builder
    public DeleteBoardRequest(Long boardId) {
        this.boardId = boardId;
    }

    public static DeleteBoardRequest of(Board board) {
        return DeleteBoardRequest.builder()
                .boardId(board.getId())
                .build();
    }
}
