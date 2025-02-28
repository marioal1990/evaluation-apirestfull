package cl.api.apiuser.controller;

import cl.api.apiuser.bean.request.LoginControllerRequest;
import cl.api.apiuser.bean.request.RegistroControllerRequest;
import cl.api.apiuser.bean.response.UserControllerResponse;
import cl.api.apiuser.service.UserService;
import cl.api.apiuser.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de los métodos HTTP del usuario
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    private final ValidationUtil validationUtil;

    @Autowired
    public UserController(UserService userService, ValidationUtil validationUtil) {
        this.userService = userService;
        this.validationUtil = validationUtil;
    }

    /**
     * Realiza la validación del logueo mediante usuario y contraseña
     * @param request Objeto de tipo {@code LoginControllerRequest} que representa la entrada ha validar
     * @return Retorna objeto ya validado con mensaje de salida en JSON
     */
    @PostMapping(value = "/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserControllerResponse> login(@RequestBody LoginControllerRequest request) {
        log.info("/login loading requests: {}", request.toString());
        HttpStatusCode status = HttpStatus.OK;
        this.validationUtil.emailValid(request.getEmail());
        UserControllerResponse userControllerResponse = this.userService.login(request);
        return new ResponseEntity<>(userControllerResponse, status);
    }

    /**
     * Método HTTP que registra a un nuevo usuario
     * @param request Objeto que representa la entrada JSON para el registro del nuevo usuario
     * @return Objeto que representa el mensaje de salida en JSON
     */
    @PostMapping(value = "/registro",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserControllerResponse> registro(@RequestBody RegistroControllerRequest request) {
        log.info("/registro loading requests: {}", request.toString());
        HttpStatusCode status = HttpStatus.OK;
        this.validationUtil.emailValid(request.getEmail());
        this.validationUtil.passwordValid(request.getPassword());
        UserControllerResponse userControllerResponse = this.userService.registro(request);
        return new ResponseEntity<>(userControllerResponse, status);
    }

    /**
     * Método HTTP que prueba el controlador
     * @return Objeto que representa el mensaje de salida en JSON
     */
    @PostMapping(value = "/hearthbeat")
    public ResponseEntity<UserControllerResponse> hearthbeat() {
        log.info("/hearthbeat loading");
        UserControllerResponse userControllerResponse = new UserControllerResponse();
        userControllerResponse.setMensaje("Hearthbeat UserController OK");
        return new ResponseEntity<>(userControllerResponse, HttpStatus.OK);
    }
}
