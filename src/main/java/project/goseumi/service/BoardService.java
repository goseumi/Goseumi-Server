package project.goseumi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.request.BoardRequestsDto;
import project.goseumi.controller.dto.response.BoardResponseDto;
import project.goseumi.controller.dto.response.SuccessResponseDto;
import project.goseumi.domain.Board;
import project.goseumi.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    // 데이터를 저장하거나 조회하려면 실제 데이터에 접근해야 하므로 Repository 객체 선언
    private final BoardRepository boardRepository;

    // 전체 목록 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getPosts() {
        return boardRepository.findAllByOrderByUpdatedAtDesc().stream()
                .map(BoardResponseDto::new) // Dto로 바꿔줌
                .toList(); // dto로 바꾼 데이터들을 list로 바꾸기
    }

    /*// 게시글 작성
    @Transactional
    public BoardResponseDto createPost(BoardRequestsDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
        boardRepository.save(board); // DB에 board 데이터가 저장됨
        return new BoardResponseDto(board);
    }*/

    // 선택한 게시글 조회
    @Transactional
    public BoardResponseDto getPost(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::new).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    /*// 선택한 게시글 수정
    @Transactional
    public BoardResponseDto updatePost(Long id, BoardRequestsDto boardRequestDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        board.update(boardRequestDto);
        return new BoardResponseDto(board);
    }*/

    // 게시글 삭제
    @Transactional
    public SuccessResponseDto deletePost(Long id, BoardRequestsDto boardRequestDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        boardRepository.deleteById(id);
        return new SuccessResponseDto(true);
    }
}
