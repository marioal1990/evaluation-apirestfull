package cl.api.apiuser.service;

import cl.api.apiuser.bean.model.User;
import cl.api.apiuser.bean.request.LoginControllerRequest;
import cl.api.apiuser.bean.request.RegistroControllerRequest;
import cl.api.apiuser.bean.response.UserControllerResponse;
import cl.api.apiuser.repository.UserRepository;
import cl.api.apiuser.util.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    /**
     * Método que registra un nuevo usuario
     * @param registroControllerRequest Objeto de entrada de petición desde el controlador
     * @return Objeto de salida que representa los datos del usuario nuevo
     */
    public UserControllerResponse registro(RegistroControllerRequest registroControllerRequest) {
        log.info("UserService.registro - loading requests: {}", registroControllerRequest.toString());
        Optional<User> user = this.userRepository.findByEmailAndPassword(registroControllerRequest.getEmail(), registroControllerRequest.getPassword());
        if (user.isPresent()) {
            throw new IllegalArgumentException("El correo ya registrado");
        } else {
            User newUser = ParseUtil.registroControllerRequestToUser(registroControllerRequest);
            User userCreated = this.userRepository.save(newUser);

            return ParseUtil.userToUserControllerResponse(userCreated);
        }
    }

    /**
     * Método que logea un usuario previamente creado
     * @param request Objeto de entrada de petición desde el controlador
     * @return Objeto de salida que representa los datos del usuario encontrado
     */
    public UserControllerResponse login(LoginControllerRequest request) {
        log.info("UserService.login - loading requests: {}", request.toString());
        Optional<User> user = this.userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("El usuario no existe");
        } else {
            User userFound = user.get();
            UserControllerResponse userControllerResponse =
                    ParseUtil.userToUserControllerResponse(userFound);
            userControllerResponse.setMensaje("Usuario logeado");
            return userControllerResponse;
        }
    }
}
