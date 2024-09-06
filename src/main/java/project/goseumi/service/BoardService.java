package project.goseumi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.board.CreateBoardRequest;
import project.goseumi.controller.dto.board.DeleteBoardRequest;
import project.goseumi.controller.dto.board.GetBoardResponse;
import project.goseumi.controller.dto.board.UpdateBoardRequest;
import project.goseumi.domain.Board;
import project.goseumi.domain.BoardCategory;
import project.goseumi.domain.Member;
import project.goseumi.domain.School;
import project.goseumi.domain.value.VisibleState;
import project.goseumi.repository.BoardCategoryRepository;
import project.goseumi.repository.BoardRepository;
import project.goseumi.repository.MemberRepository;
import project.goseumi.repository.SchoolRepository;

import java.time.LocalDateTime;

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

    // 게시글 수정
    @Transactional
    public void updateBoard(UpdateBoardRequest updateBoardRequest) {
        Board board = boardRepository.findById(updateBoardRequest.getBoardId())
                .orElseThrow();
        String title = updateBoardRequest.getTitle();
        String content = updateBoardRequest.getContent();

        Board updateBoard = Board.updateBoard(board, title, content);
        boardRepository.save(updateBoard);
    }

    // 게시글 상세 보기 (작성일, 제목, 내용)
    @Transactional
    public GetBoardResponse detailBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow();
        LocalDateTime createdAt = board.getCreatedAt();
        String title = board.getTitle();
        String content = board.getContent();
        GetBoardResponse getBoardResponse = new GetBoardResponse(createdAt, title, content);
        return getBoardResponse;
    }

    // 게시글 삭제 (Visible -> Blind)
    @Transactional
    public void deleteBoard(DeleteBoardRequest deleteBoardRequest) {
        Board board = boardRepository.findById(deleteBoardRequest.getBoardId())
                .orElseThrow();
        VisibleState updateState = VisibleState.BLIND;

        Board deleteBoard = Board.deleteBoard(board, updateState);
        boardRepository.save(deleteBoard);
    }
}
