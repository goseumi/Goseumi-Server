package project.goseumi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.goseumi.controller.dto.request.CreateCategoryRequestDto;
import project.goseumi.controller.dto.response.RenameCategoryResponseDto;
import project.goseumi.domain.BoardCategory;
import project.goseumi.exception.BusinessException;
import project.goseumi.exception.error.BoardCategoryError;
import project.goseumi.repository.BoardCategoryRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;

    @Transactional
    public String createCategory(CreateCategoryRequestDto createCategoryRequestDto) {
        BoardCategory newCategory = BoardCategory.of(createCategoryRequestDto);
        boardCategoryRepository.save(newCategory);
        return newCategory.getName();
    }

    @Transactional
    public RenameCategoryResponseDto renameCategory(Long id, CreateCategoryRequestDto createCategoryRequestDto) {
        BoardCategory findBoardCategory = boardCategoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BoardCategoryError.NOT_FOUND_CATEGORY_BY_ID));
        String before = findBoardCategory.getName();

        findBoardCategory.rename(createCategoryRequestDto.getName());

        String after = findBoardCategory.getName();

        return new RenameCategoryResponseDto(before, after);

    }

    @Transactional
    public void deleteCategory(Long id) {
        BoardCategory findBoardCategory = boardCategoryRepository.findById(id).orElseThrow(
                () -> new BusinessException(BoardCategoryError.NOT_FOUND_CATEGORY_BY_ID));

        findBoardCategory.visibleDeleted();
    }
}
