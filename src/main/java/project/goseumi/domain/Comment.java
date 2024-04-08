package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import project.goseumi.domain.value.BoardState;

import java.util.List;

@Entity
@Getter
public class Comment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany
    @JoinColumn(name = "child_comment_id")
    private List<Comment> childComments;

    private String comment;

    @Enumerated(EnumType.STRING)
    private BoardState state;
}
