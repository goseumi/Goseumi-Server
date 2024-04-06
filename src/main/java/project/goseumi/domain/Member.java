package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import project.goseumi.domain.value.Role;
import project.goseumi.domain.value.UserStatus;

@Getter
@Entity
public class Member extends BaseEntity {

    private Role role;
    private String phoneNumber;
    private String email;
    private String password;
    private UserStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;


    private Boolean withdraw; //회원탈퇴 여부
    private Boolean mailAuth;
    private Boolean schoolAuth;

}
