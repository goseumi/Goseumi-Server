package project.goseumi.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.base.PageDto;
import project.goseumi.controller.dto.school.SchoolResponse;
import project.goseumi.domain.School;

import java.util.List;

import static project.goseumi.domain.QSchool.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SchoolService {

    private final EntityManager em;


    /**
     * 학교 리스트 검색
     */
    public List<SchoolResponse> getSchoolsBySchoolName(String schulNm, PageDto page) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        PageRequest pageRequest = PageRequest.of(page.getCurrentPage(), 10);

        List<School> result = query.selectFrom(school)
                .where(
                        getLikeSchulNm(schulNm))
                .orderBy(school.schulNm.asc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        //페이징 처리용 토탈 카운트 반환
        Long total = query.select(school.count())
                .from(school)
                .where(
                        getLikeSchulNm(schulNm))
                .fetchOne();

        Page<School> schools = new PageImpl<>(result, pageRequest, total);
        page.updateTotalPages(schools.getTotalPages());

        return schools.stream()
                .map(SchoolResponse::of)
                .toList();
    }

//    public List<SchoolResponse> getBasicSchools(Long id, String name, Long addressNumber, String address, PageDto page) {
//        JPAQueryFactory query = new JPAQueryFactory(em);
//        PageRequest pageRequest = PageRequest.of(page.getCurrentPage(), 10);
//
//        //동적쿼리 리스트 반환
//        List<School> result = query.selectFrom(school)
//                .where(
//                        getLikeId(id),
//                        getLikeName(name),
//                        getLikeAddressNumber(addressNumber),
//                        getLikeAddress(address))
//                .orderBy(school.name.asc())
//                .offset(pageRequest.getOffset())
//                .limit(pageRequest.getPageSize())
//                .fetch();
//
//        //페이징 처리용 토탈 카운트 반환
//        Long total = query.select(school.count())
//                .from(school)
//                .where(
//                        getLikeId(id),
//                        getLikeName(name),
//                        getLikeAddressNumber(addressNumber),
//                        getLikeAddress(address))
//                .fetchOne();
//
//        Page<School> schools = new PageImpl<>(result, pageRequest, total);
//        page.updateTotalPages(schools.getTotalPages());
//
//        return schools.stream()
//                .map(SchoolResponse::of)
//                .toList();
//    }

    /**
     * 학교 검색 동적쿼리 조건 - %도로명 주소%
     */
//    private BooleanExpression getLikeAddress(String address) {
//        return !address.equals("") ? school.address.like("%" + address + "%") : null;
//    }
//
//    /**
//     * 학교 검색 동적쿼리 조건 - %도로명번호%
//     */
//    private BooleanExpression getLikeAddressNumber(Long addressNumber) {
//        return addressNumber != null ? school.addressNumber.like("%" + addressNumber + "%") : null;
//    }
//
    /**
     * 학교 검색 동적쿼리 조건 - %학교명%
     */
    private BooleanExpression getLikeSchulNm(String schulNm) {
        return !schulNm.equals("") ? school.schulNm.like("%" + schulNm + "%") : null;
    }
//
//    /**
//     * 학교 검색 동적쿼리 조건 - %아이디%
//     * 굳이 아이디는 동적쿼리 필요한가 싶음 - 뭐 필요없다하면 그때가서 빼면되니깐
//     */
//    private BooleanExpression getLikeId(Long id) {
//        return id != null ? school.id.like("%" + id + "%") : null;
//    }

//    @Transactional
//    public void modified(SchoolModifiedRequest schoolModifiedRequest) {
//        School school = schoolRepository.findById(schoolModifiedRequest.getId())
//                .orElseThrow(() -> new BusinessException("Not Found School By Id"));
//
//        school.modified(schoolModifiedRequest);
//    }

//    @Transactional
//    public void register(SchoolRegisterRequest request) {
//        School school = School.of(request);
//
//        schoolRepository.save(school);
//    }
}