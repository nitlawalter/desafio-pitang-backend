package br.com.tce.desafiopitangbackend.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ErrorResponse("Missing fields: " + errorMessage, 400));
    }

    @ExceptionHandler(MissingFieldsException.class)
    public ResponseEntity<ErrorResponse> handleMissingFieldsException(MissingFieldsException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage(), 400));
    }
}

