package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import project.goseumi.domain.value.Role;
import project.goseumi.domain.value.UserStatus;

@Getter
@Entity
public class Member extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Role role; //유저 역할 USER, ADMIN, GUEST

    private String phoneNumber; //전화번호

    private String nickname; //닉네임

    private String email; //이메일

    private String password; //비밀번호

    @Enumerated(EnumType.STRING)
    private UserStatus status; //유저 상태 ACTIVE, BANNED, DORMANCY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    private Boolean withdraw; //회원탈퇴 여부

    private Boolean mailAuth; //메일인증 여부

    private Boolean schoolAuth; //학교인증 여부

}
