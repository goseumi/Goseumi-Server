package project.goseumi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.goseumi.domain.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
