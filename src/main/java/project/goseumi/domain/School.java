package project.goseumi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import project.goseumi.controller.dto.request.SchoolModifiedRequest;

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

    public void modified(SchoolModifiedRequest request) {
        this.name = request.getName();
        this.address = request.getAddress();
        this.addressNumber = request.getAddressNumber();
        this.pageUrl = request.getPageUrl();
    }

}
