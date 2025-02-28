package cl.api.apiuser.bean.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginControllerRequest {

    private String email;
    private String password;
}
