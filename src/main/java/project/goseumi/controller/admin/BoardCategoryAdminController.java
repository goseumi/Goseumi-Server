package project.goseumi.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.goseumi.service.BoardCategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/board-category")
public class BoardCategoryAdminController {

    private final BoardCategoryService boardCategoryService;

}
