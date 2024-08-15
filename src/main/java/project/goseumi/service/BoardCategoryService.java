package project.goseumi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.base.PageDto;
import project.goseumi.controller.admin.dto.category.CreateCategoryRequestDto;
import project.goseumi.controller.admin.dto.category.CategoryResponse;
import project.goseumi.controller.admin.dto.category.RenameCategoryResponseDto;
import project.goseumi.domain.BoardCategory;
import project.goseumi.exception.BusinessException;
import project.goseumi.exception.error.BoardCategoryError;
import project.goseumi.repository.BoardCategoryRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;

    /**
     * 카테고리 생성 서비스 로직
     * @param createCategoryRequestDto
     */
    @Transactional
    public String createCategory(CreateCategoryRequestDto createCategoryRequestDto) {
        BoardCategory newCategory = BoardCategory.of(createCategoryRequestDto);
        boardCategoryRepository.save(newCategory);
        return newCategory.getName();
    }

    /**
     * 카테고리 이름 수정 서비스 로직
     * @param id
     * @param createCategoryRequestDto
     */
    @Transactional
    public RenameCategoryResponseDto renameCategory(Long id, CreateCategoryRequestDto createCategoryRequestDto) {
        BoardCategory findBoardCategory = boardCategoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BoardCategoryError.NOT_FOUND_CATEGORY_BY_ID));
        String before = findBoardCategory.getName();

        findBoardCategory.rename(createCategoryRequestDto.getName());

        String after = findBoardCategory.getName();

        return new RenameCategoryResponseDto(before, after);

    }

    /**
     * 카테고리 삭제 서비스 로직
     * @param id
     */
    @Transactional
    public void deleteCategory(Long id) {
        BoardCategory findBoardCategory = boardCategoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BoardCategoryError.NOT_FOUND_CATEGORY_BY_ID));

        findBoardCategory.visibleDeleted();
    }

    /**
     * 카테고리 재활성화 서비스 로직
     * @param id
     */
    @Transactional
    public void activeCategory(Long id) {
        BoardCategory findBoardCategory = boardCategoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BoardCategoryError.NOT_FOUND_CATEGORY_BY_ID));

        findBoardCategory.visibleActive();
    }

    /**
     * 카테고리 리스트 반환 서비스 로직
     */
    public List<CategoryResponse> getBasicCategoryList(PageDto page) {
        PageRequest pageRequest = PageRequest.of(page.getCurrentPage(), 10);

        Page<BoardCategory> categories = boardCategoryRepository.findAll(pageRequest);

        page.updateTotalPages(categories.getTotalPages());

        List<CategoryResponse> boardCategoryResponseList = categories.stream()
                .map(CategoryResponse::of)
                .toList();

        return boardCategoryResponseList;
    }
}
