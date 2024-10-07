package project.goseumi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.base.PageDto;
import project.goseumi.controller.dto.school.SchoolResponse;
import project.goseumi.domain.School;
import project.goseumi.repository.SchoolRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchoolService {

    private final SchoolRepository schoolRepository;

    /**
     * 학교 리스트 검색
     */
    public List<SchoolResponse> getSchoolsBySchoolName(String schulNm, PageDto pageDto) {
        PageRequest pageRequest = PageRequest.of(pageDto.getCurrentPage() - 1, 10,
                Sort.by(Sort.Order.asc("schulNm")));

        Page<School> schools = schoolRepository.findBySchulNmContains(schulNm, pageRequest);
        pageDto.updateTotalPages(schools.getTotalPages());

        return schools.stream()
                .map(SchoolResponse::of)
                .toList();
    }
}