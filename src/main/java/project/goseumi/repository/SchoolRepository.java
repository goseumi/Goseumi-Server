package project.goseumi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.goseumi.controller.dto.base.PageDto;
import project.goseumi.domain.School;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    Page<School> findBySchulNmContains(String schulNm, Pageable pageable);
}
