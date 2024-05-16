package project.goseumi.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.goseumi.controller.dto.base.ResponseDto;
import project.goseumi.controller.dto.request.CreateCategoryRequestDto;
import project.goseumi.controller.dto.response.CreateCategoryResponseDto;
import project.goseumi.controller.dto.response.RenameCategoryResponseDto;
import project.goseumi.service.BoardCategoryService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/board-category")
public class BoardCategoryAdminController {

    private final BoardCategoryService boardCategoryService;

    @PostMapping("/new")
    public ResponseDto<CreateCategoryResponseDto> newBoardCategory(@Valid @RequestBody CreateCategoryRequestDto createCategoryRequestDto) {
        String categoryName = boardCategoryService.createCategory(createCategoryRequestDto);
        CreateCategoryResponseDto createCategoryResponseDto = new CreateCategoryResponseDto(categoryName);
        return ResponseDto.of(createCategoryResponseDto, "created New Category");
    }

    @PatchMapping("/rename/{id}")
    public ResponseDto<RenameCategoryResponseDto> rename(@PathVariable("id") Long id, @Valid @RequestBody CreateCategoryRequestDto createCategoryRequestDto) {
        RenameCategoryResponseDto renameCategoryResponseDto = boardCategoryService.renameCategory(id, createCategoryRequestDto);
        return ResponseDto.of(renameCategoryResponseDto, "renamed Complete Category");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDto<String> deleteCategory(@PathVariable("id") Long id) {
        boardCategoryService.deleteCategory(id);
        return ResponseDto.of("deleted BoardCategory by id " + id);
    }
}
