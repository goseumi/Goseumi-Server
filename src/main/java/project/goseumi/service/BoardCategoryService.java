package project.goseumi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.goseumi.controller.dto.request.CreateCategoryRequestDto;
import project.goseumi.domain.BoardCategory;
import project.goseumi.repository.BoardCategoryRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;

    public String createCategory(CreateCategoryRequestDto createCategoryRequestDto) {
        BoardCategory newCategory = BoardCategory.of(createCategoryRequestDto);
        boardCategoryRepository.save(newCategory);
        return newCategory.getName();
    }

}
