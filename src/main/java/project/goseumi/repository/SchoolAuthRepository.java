package project.goseumi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.goseumi.domain.SchoolAuth;

public interface SchoolAuthRepository extends JpaRepository<SchoolAuth, Long> {
}
