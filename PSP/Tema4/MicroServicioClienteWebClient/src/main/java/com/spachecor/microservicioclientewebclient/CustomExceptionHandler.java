package com.spachecor.microservicioclientewebclient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

// Anotación que indica que esta clase es un controlador global de excepciones en toda la aplicación
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Maneja específicamente la excepción de tipo Unauthorized lanzada por WebClient.
     * Esto ocurre cuando el servicio remoto devuelve un estado 401 Unauthorized.
     *
     * @param ex La excepción que se ha lanzado.
     * @return Una respuesta con el estado 401 y un mensaje personalizado.
     */
    @ExceptionHandler(WebClientResponseException.Unauthorized.class)
    public ResponseEntity<String> handleUnauthorizedException(WebClientResponseException.Unauthorized ex) {
        // Retorna un ResponseEntity con estado 401 y un mensaje indicando que no está autorizado
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + ex.getMessage());
    }

    /**
     * Maneja todas las demás excepciones de WebClientResponseException, que incluyen
     * otros errores HTTP (404, 403, 500, etc.).
     *
     * @param ex La excepción que se ha lanzado.
     * @return Una respuesta con el código de estado específico y un mensaje de error.
     */
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientException(WebClientResponseException ex) {
        // Retorna una respuesta con el estado específico y el mensaje de error recibido
        return ResponseEntity.status(ex.getStatusCode()).body("Error: " + ex.getMessage());
    }
}
