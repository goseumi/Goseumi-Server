package project.goseumi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.goseumi.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}