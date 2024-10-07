package project.goseumi.controller.dto.board;

import lombok.Builder;
import lombok.Getter;
import project.goseumi.domain.Board;

@Getter
public class CreateBoardRequest { // 사용자가 게시글을 작성하면 서버에 보내줄 데이터들

    private Long boardCategoryId;
    private Long schoolId;
    private String title;
    private String content;

    @Builder
    public CreateBoardRequest(Long boardCategoryId, Long schoolId, String title, String content) {
        this.boardCategoryId = boardCategoryId;
        this.schoolId = schoolId;
        this.title = title;
        this.content = content;
    }

    protected CreateBoardRequest() {
    }

    public static CreateBoardRequest of(Board board) {
        return CreateBoardRequest.builder()
                .boardCategoryId(board.getBoardCategory().getId())
                .schoolId(board.getSchool().getSdSchulCode())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
