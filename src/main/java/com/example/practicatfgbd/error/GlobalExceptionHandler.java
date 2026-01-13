package com.example.practicatfgbd.error;



import com.example.practicatfgbd.payload.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

// Esta anotación indica que esta clase proporciona manejo global de excepciones para controladores REST.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Este método maneja excepciones del tipo ResourceNotFoundException.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest) {
        // Se crea una respuesta de API con el mensaje de la excepción y la descripción de la solicitud web.
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        // Se devuelve una respuesta con el estado HTTP 404 (NOT_FOUND) y la respuesta de la API.
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    // Este método maneja excepciones del tipo BadRequestException.
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> handlerBadRequestException(BadRequestException exception,
                                                                  WebRequest webRequest) {
        // Se crea una respuesta de API con el mensaje de la excepción y la descripción de la solicitud web.
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        // Se devuelve una respuesta con el estado HTTP 500 (INTERNAL_SERVER_ERROR) y la respuesta de la API.
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
