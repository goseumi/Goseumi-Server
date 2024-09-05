package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import project.goseumi.domain.value.VisibleState;

@Getter
@Entity // DB에 저장할 엔티티이므로 붙여줌
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

    @Builder
    protected Board(Member member, BoardCategory boardCategory, School school, String title, String content, VisibleState state) {
        this();
        this.member = member;
        this.boardCategory = boardCategory;
        this.school = school;
        this.title = title;
        this.content = content;
        this.state = state;
    }

    public static Board createBoard(BoardCategory boardCategory, School school, String title, String content, VisibleState state) {
        return Board.builder()
                .boardCategory(boardCategory)
                .school(school)
                .title(title)
                .content(content)
                .state(state)
                .build();
    }

    public static Board deleteBoard() {
        return Board.builder()
                .
    }
}
