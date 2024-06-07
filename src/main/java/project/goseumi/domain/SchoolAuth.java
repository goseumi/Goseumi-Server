package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Builder;
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

    protected SchoolAuth() {

    }

    @Builder
    protected SchoolAuth(Member member, School school, String url) {
        this.member = member;
        this.school = school;
        this.url = url;
    }

    public static SchoolAuth of(Member member, School school, String url) {
        return SchoolAuth.builder()
                .member(member)
                .school(school)
                .url(url)
                .build();
    }

    public void authAccess() {
        this.accept = BooleanState.YES;
        this.rejectReason = "access";
    }

    public void authReject(String message) {
        this.accept = BooleanState.NO;
        this.rejectReason = message;
    }
}
