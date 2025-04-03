package com.pessoa.apipessoa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandles {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails("404", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationErrorResponse response = new ValidationErrorResponse(
                "400",
                "Erro de validação",
                LocalDateTime.now(),
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Classe para erros de validação
    public static class ValidationErrorResponse {
        private String code;
        private String message;
        private LocalDateTime timestamp;
        private Map<String, String> fieldErrors;

        public ValidationErrorResponse(String code, String message, LocalDateTime timestamp, Map<String, String> fieldErrors) {
            this.code = code;
            this.message = message;
            this.timestamp = timestamp;
            this.fieldErrors = fieldErrors;
        }

        // Getters e Setters
        public String getCode() { return code; }
        public String getMessage() { return message; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public Map<String, String> getFieldErrors() { return fieldErrors; }
    }

    // Classe para detalhes de erro
    public static class ErrorDetails {
        private String code;
        private String message;

        public ErrorDetails(String code, String message) {
            this.code = code;
            this.message = message;
        }

        // Getters e Setters
        public String getCode() { return code; }
        public String getMessage() { return message; }
        public void setCode(String code) { this.code = code; }
        public void setMessage(String message) { this.message = message; }
    }

    @ExceptionHandler(CpfJaCadastradoException.class)
    public ResponseEntity<ErrorDetails> handleCpfJaCadastradoException(CpfJaCadastradoException ex) {
        // Utiliza a mensagem da exceção para criar a resposta
        ErrorDetails errorDetails = new ErrorDetails("400", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}