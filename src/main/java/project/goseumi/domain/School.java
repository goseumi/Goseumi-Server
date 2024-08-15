package project.goseumi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import project.goseumi.controller.admin.dto.school.SchoolModifiedRequest;
import project.goseumi.controller.admin.dto.school.SchoolRegisterRequest;

@Getter
@Entity
public class School extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 50)
    private String address;

    @Column
    private Long addressNumber;

    @Column
    private String pageUrl;

    protected School() {

    }

    @Builder
    protected School(String name, String address, Long addressNumber, String pageUrl) {
        this.name = name;
        this.address = address;
        this.addressNumber = addressNumber;
        this.pageUrl = pageUrl;
    }

    public static School of(SchoolRegisterRequest schoolRegisterRequest) {
        return School.builder()
                .name(schoolRegisterRequest.getName())
                .address(schoolRegisterRequest.getAddress())
                .addressNumber(schoolRegisterRequest.getAddressNumber())
                .pageUrl(schoolRegisterRequest.getPageUrl())
                .build();
    }

    public void modified(SchoolModifiedRequest request) {
        this.name = request.getName();
        this.address = request.getAddress();
        this.addressNumber = request.getAddressNumber();
        this.pageUrl = request.getPageUrl();
    }

}
