package project.goseumi.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.goseumi.controller.dto.base.ResponseDto;
import project.goseumi.controller.dto.request.SchoolAuthAccessRequest;
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
}
