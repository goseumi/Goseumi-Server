package project.goseumi.controller.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    private String password;

    protected LoginRequest() {

    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
