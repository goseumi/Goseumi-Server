package project.goseumi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.goseumi.controller.dto.board.CreateBoardRequest;
import project.goseumi.controller.dto.board.DeleteBoardRequest;
import project.goseumi.controller.dto.board.GetBoardResponse;
import project.goseumi.controller.dto.board.UpdateBoardRequest;
import project.goseumi.service.BoardService;
import project.goseumi.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @PostMapping("/create")
    public void createBoard(@RequestBody CreateBoardRequest createBoardRequest, HttpServletRequest request) {
        String username = memberService.getUserEmailFromToken(request);
        boardService.createBoard(createBoardRequest, username);
    }

    @PatchMapping("/update")
    public void updateBoard(@RequestBody UpdateBoardRequest updateBoardRequest, HttpServletRequest request) {
        String username = memberService.getUserEmailFromToken(request);
        boardService.updateBoard(updateBoardRequest, username);
    }

    @GetMapping("/detail")
    public GetBoardResponse getBoard(@RequestParam("boardId") Long boardId) {
        GetBoardResponse getBoardResponse = boardService.detailBoard(boardId);
        return getBoardResponse;
    }

    @DeleteMapping("/delete")
    public void deleteBoard(@Valid @RequestBody DeleteBoardRequest deleteBoardRequest) {
        boardService.deleteBoard(deleteBoardRequest);
    }
}
