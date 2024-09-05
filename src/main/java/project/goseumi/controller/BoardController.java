package project.goseumi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.goseumi.controller.dto.board.CreateBoardRequest;
import project.goseumi.controller.dto.board.DeleteBoardRequest;
import project.goseumi.service.BoardService;
import project.goseumi.service.MemberService;

import java.net.http.HttpRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @PostMapping("/create")
    public void createBoard(@Valid @RequestBody CreateBoardRequest createBoardRequest, HttpServletRequest request) {
        String username = memberService.getUserEmailFromToken(request);
        boardService.createBoard(createBoardRequest, username);
    }

    @DeleteMapping("/delete")
    public void deleteBoard(@Valid @RequestBody DeleteBoardRequest deleteBoardRequest) {

    }
}
