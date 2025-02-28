package cl.api.apiuser.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Clase Utilitaria de validaciones personalizadas
 */
@Component
@Slf4j
public class ValidationUtil {

    private final String passwordRegex;

    public ValidationUtil(@Value("${app.validator.password.regexp}") String passwordRegex) {
        this.passwordRegex = passwordRegex;
    }

    /**
     * Validación del correo electrónico
     * @param email Cadena de caracteres que representa al correo electrónico
     */
    public void emailValid(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email está vacio");
        }

        boolean regexEmail = Pattern.compile(ConstantesUtil.EMAIL_REGEX)
                .matcher(email)
                .matches();
        if (!regexEmail) {
            log.error("El email {} ingresado no cumple con el formato correcto", email);
            throw new IllegalArgumentException("El email ingresado no cumple con el formato correcto");
        }
    }

    /**
     * Validación de la contraseña
     * @param password Cadena de caracteres que representa a la contraseña del usuario
     */
    public void passwordValid(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("El password es vacio");
        }

        boolean regexPass = Pattern.compile(passwordRegex)
                .matcher(password)
                .matches();
        if (!regexPass) {
            log.error("El password {} ingresado no cumple con el formato correcto", password);
            throw new IllegalArgumentException("El password ingresado no cumple con el formato correcto");
        }
    }


}
