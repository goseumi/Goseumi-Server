package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import project.goseumi.controller.dto.request.SignUpRequest;
import project.goseumi.domain.value.BooleanState;
import project.goseumi.domain.value.Role;
import project.goseumi.domain.value.UserStatus;

@Getter
@Entity
public class Member extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Role role; //유저 역할 USER, ADMIN, GUEST

    private String phone; //전화번호

    private String nickname; //닉네임

    private String email; //이메일

    private String password; //비밀번호

    @Enumerated(EnumType.STRING)
    private UserStatus status; //유저 상태 ACTIVE, BANNED, DORMANCY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Enumerated(EnumType.STRING)
    private BooleanState withdraw; //회원탈퇴 여부

    @Enumerated(EnumType.STRING)
    private BooleanState mailAuth; //메일인증 여부

    @Enumerated(EnumType.STRING)
    private BooleanState schoolAuth; //학교인증 여부

    protected Member() {
        withdraw = BooleanState.NO;
        schoolAuth = BooleanState.NO;
        mailAuth = BooleanState.NO;
        role = Role.USER;
        status = UserStatus.ACTIVE;
    }

    @Builder
    protected Member(String email, String password, String phone, String nickname) {
        this();
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.nickname = nickname;
    }

    public static Member signUp(SignUpRequest signUpRequest, String encodedPassword) {
        return Member.builder()
                .email(signUpRequest.getEmail())
                .password(encodedPassword)
                .phone(signUpRequest.getPhone())
                .nickname(signUpRequest.getNickname())
                .build();
    }
}
