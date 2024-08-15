package project.goseumi.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.goseumi.controller.dto.base.PageDto;
import project.goseumi.controller.dto.base.PageResponseDto;
import project.goseumi.controller.dto.base.ResponseDto;
import project.goseumi.controller.admin.dto.category.CreateCategoryRequestDto;
import project.goseumi.controller.admin.dto.category.CategoryResponse;
import project.goseumi.controller.admin.dto.category.CreateCategoryResponseDto;
import project.goseumi.controller.admin.dto.category.RenameCategoryResponseDto;
import project.goseumi.service.BoardCategoryService;

import java.util.List;

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
        return ResponseDto.of("deleted BoardCategory by id : " + id);
    }

    @PatchMapping("/active/{id}")
    public ResponseDto<String> activeCategory(@PathVariable("id") Long id) {
        boardCategoryService.activeCategory(id);
        return ResponseDto.of("active BoardCategory by id : " + id);
    }

    @GetMapping("")
    public PageResponseDto<List<CategoryResponse>> getBasicCategoryList(
            @RequestParam(name = "page", defaultValue = "0") int page
    ){
        PageDto pageDto = PageDto.of(page);

        List<CategoryResponse> basicCategoryList = boardCategoryService.getBasicCategoryList(pageDto);
        return PageResponseDto.of(basicCategoryList, "Get BoardCategory List", pageDto);
    }
}