package project.goseumi.controller.dto.response;

import lombok.Getter;

@Getter
public class SignUpResponse {

    private final Long newSignUpMemberId;

    public SignUpResponse(Long newSignUpMemberId) {
        this.newSignUpMemberId = newSignUpMemberId;
    }
}
