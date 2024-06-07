package project.goseumi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.goseumi.controller.dto.base.ResponseDto;
import project.goseumi.controller.dto.request.SchoolAuthRequest;
import project.goseumi.controller.dto.request.SignUpRequest;
import project.goseumi.controller.dto.response.SignUpResponse;
import project.goseumi.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseDto<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        Long newSignUpMemberId = memberService.createNewAccount(signUpRequest);
        log.info("SignUp Member : " + newSignUpMemberId);
        SignUpResponse signUpResponse = new SignUpResponse(newSignUpMemberId);
        return ResponseDto.of(signUpResponse, "new Member account has been created.");
    }

    @PostMapping("/schoolAuth")
    public ResponseDto<Object> newSchoolAuth(HttpServletRequest request, @Valid @RequestBody SchoolAuthRequest schoolAuthRequest) {
        Long SchoolAuthId = memberService.newSchoolAuth(request, schoolAuthRequest);
        log.info("create new SchoolAuth : " + SchoolAuthId);
        return ResponseDto.of("new SchoolAuth has been created.");
    }
}
