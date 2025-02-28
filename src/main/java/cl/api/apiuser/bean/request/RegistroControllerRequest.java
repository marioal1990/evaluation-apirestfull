package cl.api.apiuser.bean.request;

import cl.api.apiuser.bean.dto.PhoneDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistroControllerRequest {

    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
