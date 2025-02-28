package cl.api.apiuser.bean.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    private Long number;
    private String cityCode;
    private String contryCode;
}
