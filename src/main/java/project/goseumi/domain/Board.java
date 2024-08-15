package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import project.goseumi.controller.dto.request.BoardRequestsDto;
import project.goseumi.domain.value.VisibleState;

@Entity // DB에 저장할 엔티티이므로 붙여줌
@Getter
public class Board extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_id")
    private BoardCategory boardCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Column(nullable = false) // 객체 필드를 테이블의 컬럼으로 맵핑
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private VisibleState state;

    public Board() {

    }

    public Board(BoardRequestsDto boardRequestDto, Member member, BoardCategory boardCategory, School school) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.member = member;
        this.boardCategory = boardCategory;
        this.school = school;
    }


    public void update(BoardRequestsDto boardRequestDto, Member member, BoardCategory boardCategory, School school) {
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.member = member;
        this.boardCategory = boardCategory;
        this.school = school;
    }

}
