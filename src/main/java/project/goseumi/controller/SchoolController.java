package project.goseumi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.goseumi.controller.dto.base.PageDto;
import project.goseumi.controller.dto.base.PageResponseDto;
import project.goseumi.controller.dto.school.SchoolResponse;
import project.goseumi.service.SchoolService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/school")
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public PageResponseDto<List<SchoolResponse>> getSchoolsByName(
            @RequestParam(name = "schul-nm", defaultValue = "") String schulNm,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        PageDto pageDto = PageDto.of(page);
        List<SchoolResponse> result = schoolService.getSchoolsBySchoolName(schulNm, pageDto);
        return PageResponseDto.of(result, "Get School List, Page = " + page, pageDto);
    }

//    @GetMapping("")
//    public PageResponseDto<List<SchoolResponse>> getBasicSchoolList(
//            @RequestParam(name = "id", defaultValue = "") Long id,
//            @RequestParam(name = "name", defaultValue = "") String name,
//            @RequestParam(name = "addressNumber", defaultValue = "") Long addressNumber,
//            @RequestParam(name = "address", defaultValue = "") String address,
//            @RequestParam(name = "page", defaultValue = "0") int page
//    ) {
//        PageDto pageDto = PageDto.of(page);
//
//        List<SchoolResponse> basicSchools = schoolService.getBasicSchools(id, name, addressNumber, address, pageDto);
//        return PageResponseDto.of(basicSchools, "Get School List, Page = " + page, pageDto);
//    }

}
