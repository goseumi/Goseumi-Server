package project.goseumi.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import project.goseumi.domain.Board;
import project.goseumi.domain.BoardCategory;
import project.goseumi.domain.Member;
import project.goseumi.domain.School;

@Getter
public class BoardRequestsDto {

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private Long boardCategoryId;

    private Long memberId;

    private Long schoolId;

    protected BoardRequestsDto() {

    }

    public BoardRequestsDto(String title, String content, Long boardCategoryId, Long memberId, Long schoolId) {
        this.title = title;
        this.content = content;
        this.boardCategoryId = boardCategoryId;
        this.memberId = memberId;
        this.schoolId = schoolId;
    }
}
