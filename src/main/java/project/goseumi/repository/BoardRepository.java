package project.goseumi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.goseumi.domain.Board;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // JPA를 사용해서 DB에 테이블 정보를 생성, 저장, 조회할 것이므로 JpaRepository를 상속 받음
    List<Board> findAllByOrderByUpdatedAtDesc();
}
