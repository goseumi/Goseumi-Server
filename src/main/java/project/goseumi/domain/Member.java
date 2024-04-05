package project.goseumi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Member extends BaseEntity {

    private Role role;
    private String phoneNumber;
    private String email;

}
