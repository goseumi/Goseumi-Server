package project.goseumi.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.goseumi.controller.dto.base.ResponseDto;
import project.goseumi.controller.dto.request.SchoolModifiedRequest;
import project.goseumi.service.SchoolService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin/school")
public class SchoolAdminController {

    private final SchoolService schoolService;

    @PatchMapping("/info")
    public ResponseDto<String> modifiedSchool(@Valid @RequestBody SchoolModifiedRequest schoolModifiedRequest) {
        schoolService.modified(schoolModifiedRequest);

        return ResponseDto.of("Success modified school information");
    }
}
