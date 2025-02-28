package cl.api.apiuser.exception;

import cl.api.apiuser.bean.ApiError;
import cl.api.apiuser.bean.response.UserControllerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Método que captura una excepcion global
     * @param exception Excepcion general capturada
     * @return {@code ResponseEntity<UserControllerResponse>} Objeto con excepcion capturado
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserControllerResponse> handleGeneralException(Exception exception) {
        log.error(exception.getMessage(), exception);
        UserControllerResponse userControllerResponse = new UserControllerResponse();
        userControllerResponse.setMensaje(exception.getMessage());
        return new ResponseEntity<>(userControllerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Clase para estructurar la respuesta de error
     */
    @Getter
    @Setter
    @AllArgsConstructor
    static class ErrorResponse {

        private int status;
        private String error;
        private String message;
        private LocalDateTime timestamp;
    }
}
