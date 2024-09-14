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
import project.goseumi.exception.BusinessException;
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
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException("Member not found by email: " + username));
        BoardCategory boardCategory = boardCategoryRepository.findById(createBoardRequest.getBoardCategoryId())
                .orElseThrow(() -> new BusinessException("BoardCategory not found by boardCategoryId: " + createBoardRequest.getBoardCategoryId()));
        School school = schoolRepository.findById(createBoardRequest.getSchoolId())
                .orElseThrow(() -> new BusinessException("School not found by SchoolId: " + createBoardRequest.getSchoolId()));
        String title = createBoardRequest.getTitle();
        String content = createBoardRequest.getContent();

        Board createBoard = Board.createBoard(member, boardCategory, school, title, content);
        boardRepository.save(createBoard);
    }

    // 게시글 수정
    @Transactional
    public void updateBoard(UpdateBoardRequest updateBoardRequest, String username) {
        Board board = boardRepository.findById(updateBoardRequest.getBoardId())
                .orElseThrow(() -> new BusinessException("Board not found by boardId: " + updateBoardRequest.getBoardId()));
        String title = updateBoardRequest.getTitle();
        String content = updateBoardRequest.getContent();

        Member member = board.getMember();
        Member dbMember = memberRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException("Member not found by email: " + username));
        if (member.equals(dbMember)) {
            Board.updateBoard(board, title, content);
        } else {
            throw new BusinessException("Board not found by boardId: " + updateBoardRequest.getBoardId());
        }
    }

    // 게시글 상세 보기 (작성일, 제목, 내용)
    @Transactional
    public GetBoardResponse detailBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException("Board not found by boardId: " + boardId));
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
                .orElseThrow(() -> new BusinessException("Board not found by boardId: " + deleteBoardRequest.getBoardId()));
        VisibleState updateState = VisibleState.BLIND;

        Board deleteBoard = Board.deleteBoard(board, updateState);
        boardRepository.save(deleteBoard);
    }
}
