package project.goseumi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.goseumi.controller.dto.request.BoardRequestsDto;
import project.goseumi.controller.dto.response.BoardResponseDto;
import project.goseumi.controller.dto.response.SuccessResponseDto;
import project.goseumi.service.BoardService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    // 호출된 URL에 따라 메서드를 실행시킼 때, Service에서 구현된 메서드를 실행시킬 것이므로 Service 객체를 선언
    private final BoardService boardService;

    // 전체 목록 조회
    @GetMapping("/posts")
    public List<BoardResponseDto> getBoards() { // 게시글 내용 BoardResponseDto에 담아 List 형태로 Client에 보냄
        return boardService.getPosts(); // 전체 목록 가져옴
    }

    // 게시글 작성
    @PostMapping("/post")
    public BoardResponseDto createBoard(@RequestBody BoardRequestsDto boardRequestDto) {
        return boardService.createPost(boardRequestDto);
    }

    // 선택한 게시글 조회
    @GetMapping("/post/{id}") // id에 해당하는 글 조회
    public BoardResponseDto getPost(@PathVariable Long id) {
        return boardService.getPost(id);
    }

    // 선택한 게시글 수정
    @PutMapping("/post/{id}") // id에 해당하는 글 수정
    public BoardResponseDto updatePost(@PathVariable Long id, @RequestBody BoardRequestsDto boardRequestDto) throws Exception {
        return boardService.updatePost(id, boardRequestDto);
    }

    @DeleteMapping("/post/{id}")
    public SuccessResponseDto deletePost(@PathVariable Long id, @RequestBody BoardRequestsDto boardRequestDto) throws Exception {
        return boardService.deletePost(id, boardRequestDto);
    }
}
