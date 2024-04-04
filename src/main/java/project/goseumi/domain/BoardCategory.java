package project.goseumi.domain;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class BoardCategory extends BaseEntity {

    private String name;
}
