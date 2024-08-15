package project.goseumi.controller.dto.member;

import lombok.Getter;

@Getter
public class SignUpResponse {

    private final Long newSignUpMemberId;

    public SignUpResponse(Long newSignUpMemberId) {
        this.newSignUpMemberId = newSignUpMemberId;
    }
}
