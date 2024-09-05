package project.goseumi.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.board.CreateBoardRequest;
import project.goseumi.controller.dto.board.DeleteBoardRequest;
import project.goseumi.domain.Board;
import project.goseumi.domain.BoardCategory;
import project.goseumi.domain.Member;
import project.goseumi.domain.School;
import project.goseumi.domain.value.VisibleState;
import project.goseumi.repository.BoardCategoryRepository;
import project.goseumi.repository.BoardRepository;
import project.goseumi.repository.MemberRepository;
import project.goseumi.repository.SchoolRepository;

import java.net.http.HttpRequest;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final SchoolRepository schoolRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 작성
    @Transactional
    public void createBoard(CreateBoardRequest createBoardRequest, String username) {
        Member member = memberRepository.findByEmail(username).get();
        BoardCategory boardCategory = boardCategoryRepository.findById(createBoardRequest.getBoardCategoryId())
                .orElseThrow();
        School school = schoolRepository.findById(createBoardRequest.getSchoolId())
                .orElseThrow();
        String title = createBoardRequest.getTitle();
        String content = createBoardRequest.getContent();

        Board createBoard = Board.createBoard(member, boardCategory, school, title, content);
        boardRepository.save(createBoard);
    }

    /*// 게시글 삭제 (Visible -> Blind)
    @Transactional
    public void deleteBoard(DeleteBoardRequest deleteBoardRequest) {
        Board board = boardRepository.findById(deleteBoardRequest.getBoardId())
                .orElseThrow();
        VisibleState visibleState = VisibleState.valueOf("Blind");

        Board deleteBoard = Board.
    }*/
}
