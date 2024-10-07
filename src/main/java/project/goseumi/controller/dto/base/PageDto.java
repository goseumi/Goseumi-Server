package project.goseumi.controller.dto.base;

import lombok.Getter;
import project.goseumi.exception.BusinessException;

@Getter
public class PageDto {

    private final int currentPage;
    private int totalPages;

    protected PageDto(int currentPage, int totalPages) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public static PageDto of(int currentPage) {
        if (currentPage < 1) {
            throw new BusinessException("Invalid Page");
        }
        return new PageDto(currentPage, 1);
    }

    public void updateTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
