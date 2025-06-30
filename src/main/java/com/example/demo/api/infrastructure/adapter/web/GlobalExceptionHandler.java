package com.example.demo.api.infrastructure.adapter.web;

import com.example.demo.api.domain.exception.FranchiseNotFoundException;
import com.example.demo.api.infrastructure.adapter.web.dto.ErrorResponse; // Crearemos esta DTO
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice // Esta anotación combina @ControllerAdvice y @ResponseBody
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo FranchiseNotFoundException y las mapea a una respuesta 404 Not Found.
     * @param ex La excepción FranchiseNotFoundException lanzada.
     * @return Un Mono de ResponseEntity con el código de estado 404 y un cuerpo de error.
     */
    @ExceptionHandler(FranchiseNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleFranchiseNotFoundException(FranchiseNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse));
    }

    /**
     * Maneja excepciones de validación (ej. @NotBlank, @Min) de los DTOs de Request.
     * Mapea los errores de validación a una respuesta 400 Bad Request.
     * @param ex La excepción WebExchangeBindException (para WebFlux) que contiene los errores de validación.
     * @return Un Mono de ResponseEntity con el código de estado 400 y un cuerpo de error con los detalles de validación.
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationExceptions(WebExchangeBindException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Errores de validación: " + String.join(", ", errors),
                LocalDateTime.now()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse));
    }

    /**
     * Maneja excepciones genéricas no capturadas por otros handlers.
     * Mapea cualquier otra RuntimeException a una respuesta 500 Internal Server Error.
     * Es importante no exponer detalles sensibles de la excepción en producción.
     * @param ex Cualquier otra RuntimeException.
     * @return Un Mono de ResponseEntity con el código de estado 500 y un cuerpo de error.
     */
    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericRuntimeException(RuntimeException ex) {
        // En un entorno de producción, aquí podrías loggear la excepción completa
        // y retornar un mensaje más genérico al cliente por seguridad.
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Ha ocurrido un error inesperado. Por favor, intente más tarde.", // Mensaje genérico para el cliente
                LocalDateTime.now()
        );
        // Opcional: Para desarrollo, puedes incluir ex.getMessage() para depuración.
        // errorResponse.setMessage("Error interno del servidor: " + ex.getMessage());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }

    // Puedes añadir más @ExceptionHandler para otras excepciones específicas aquí
    // Por ejemplo, BranchNotFoundException, ProductNotFoundException, IllegalArgumentException, etc.
}