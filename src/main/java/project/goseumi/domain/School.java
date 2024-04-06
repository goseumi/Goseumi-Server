package project.goseumi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class School extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false)
    private Long addressNumber;

    @Column
    private String pageUrl;

}
