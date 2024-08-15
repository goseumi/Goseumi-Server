package project.goseumi.controller.dto.school;

import lombok.Builder;
import lombok.Getter;
import project.goseumi.domain.School;

@Getter
public class SchoolResponse {

    private final Long id;
    private final String name;
    private final Long addressNumber;
    private final String address;
    private final String pageUrl;

    @Builder
    protected SchoolResponse(Long id, String name, Long addressNumber, String address, String pageUrl) {
        this.id = id;
        this.name = name;
        this.addressNumber = addressNumber;
        this.address = address;
        this.pageUrl = pageUrl;
    }

    public static SchoolResponse of(School school) {
        return SchoolResponse.builder()
                .id(school.getId())
                .name(school.getName())
                .addressNumber(school.getAddressNumber())
                .address(school.getAddress())
                .pageUrl(school.getPageUrl())
                .build();
    }

}
