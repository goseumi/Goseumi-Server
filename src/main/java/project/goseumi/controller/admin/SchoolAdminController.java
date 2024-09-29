package project.goseumi.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.goseumi.controller.dto.base.ResponseDto;
import project.goseumi.controller.admin.dto.school.SchoolModifiedRequest;
import project.goseumi.controller.admin.dto.school.SchoolRegisterRequest;
import project.goseumi.service.SchoolService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/admin/school")
public class SchoolAdminController {

    private final SchoolService schoolService;

//    @PatchMapping("/info")
//    public ResponseDto<Object> modifiedSchool(@Valid @RequestBody SchoolModifiedRequest schoolModifiedRequest) {
//        schoolService.modified(schoolModifiedRequest);
//
//        return ResponseDto.of("Success modified school information");
//    }

//    @PostMapping("")
//    public ResponseDto<Object> registerSchool(@Valid @RequestBody SchoolRegisterRequest schoolRegisterRequest) {
//        schoolService.register(schoolRegisterRequest);
//
//        return ResponseDto.of("Success create new school information");
//    }
}
