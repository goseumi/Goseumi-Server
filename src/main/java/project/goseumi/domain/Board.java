package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import project.goseumi.domain.value.VisibleState;

@Entity
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

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private VisibleState state;
}
