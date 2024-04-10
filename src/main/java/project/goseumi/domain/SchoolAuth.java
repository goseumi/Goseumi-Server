package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import project.goseumi.domain.value.BooleanState;

@Entity
@Getter
public class SchoolAuth extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Enumerated(EnumType.STRING)
    private BooleanState accept;

    private String rejectReason; //인증거부사유

    private String url; //인증용 사진 학생증 url
}
