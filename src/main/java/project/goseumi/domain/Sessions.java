package project.goseumi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Sessions extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String sessionsCode;

    protected Sessions() {
        sessionsCode = "session_" + UUID.randomUUID().toString().replace("-", "");
    }
}
