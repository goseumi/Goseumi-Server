package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import project.goseumi.domain.value.VisibleState;

@Entity
@Getter
public class Market extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private Long price;

    @Enumerated(EnumType.STRING)
    private VisibleState state;

    private boolean sell;
}
