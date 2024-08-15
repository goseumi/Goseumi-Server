package project.goseumi.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import project.goseumi.domain.Board;
import project.goseumi.domain.Member;
import project.goseumi.domain.School;
import project.goseumi.domain.value.VisibleState;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private Member memberId;
    private School schoolId;
    private VisibleState state;
    /*private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;*/

    // Board 엔티티를 받아서 BoardResponseDto 객체로 만들기 위한 생성자
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.memberId = board.getMember();
        this.schoolId = board.getSchool();
        this.state = board.getState();
        /*this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();*/
    }

}
