package project.goseumi.controller.dto.school;

import lombok.Builder;
import project.goseumi.domain.School;

@Builder
public record SchoolResponse(
        Long sdSchulCode,
        String schulNm,
        String orgRdnda
) {
    public static SchoolResponse of(School school) {
        return SchoolResponse.builder()
                .sdSchulCode(school.getSdSchulCode())
                .schulNm(school.getSchulNm())
                .orgRdnda(school.getOrgRdnda())
                .build();
    }
}
