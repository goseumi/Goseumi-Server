package project.goseumi.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.goseumi.controller.dto.base.ResponseDto;
import project.goseumi.controller.admin.dto.member.SchoolAuthAccessRequest;
import project.goseumi.controller.admin.dto.member.SchoolAuthRejectRequest;
import project.goseumi.service.MemberService;

@RestController
@RequestMapping("/api/admin/member")
@RequiredArgsConstructor
@Slf4j
public class MemberAdminController {

    private final MemberService memberService;

    @PatchMapping("/schoolAuth/access")
    public ResponseDto<Object> schoolAuthAccess(@Valid @RequestBody SchoolAuthAccessRequest schoolAuthAccessRequest) {

        Long id = memberService.accessSchoolAuth(schoolAuthAccessRequest);
        log.info("Access SchoolAuth id = " + id);

        return ResponseDto.of("Accessed SchoolAuth");
    }

    @PatchMapping("/schoolAuth/reject")
    public ResponseDto<Object> schoolAuthReject(@Valid @RequestBody SchoolAuthRejectRequest schoolAuthRejectRequest) {
        Long id = memberService.rejectSchoolAuth(schoolAuthRejectRequest);
        log.info("Reject SchoolAuth id = " + id);

        return ResponseDto.of("Rejected SchoolAuth");
    }

}
