package com.interview.codingtest.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.interview.codingtest.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest reqeust) {
        String message;

        if (ex.getRootCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            // Handle constraint violations, e.g., unique constraints
            message = "Database constraint violation: " + getRootCauseMessage(ex);
        } else {
            message = "Data integrity violation: " + getRootCauseMessage(ex);
        }

        Map<String, String> response = new HashMap<>();
        response.put("error", message);

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder().apiPath(reqeust.getDescription(false))
                .errorMessage(message).errorCode(HttpStatus.CONFLICT.value()).errorTime(LocalDateTime.now()).build();

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    // Handle @Valid and @Validated validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDTO>> handleValidationExceptions(WebRequest request,
            MethodArgumentNotValidException ex) {

        List<ErrorResponseDTO> errors = ex.getBindingResult().getAllErrors().stream().map(error -> {
            return ErrorResponseDTO.builder().apiPath(request.getDescription(false))
                    .errorMessage(((FieldError) error).getField() + " : " + ((FieldError) error).getDefaultMessage())
                    .errorCode(HttpStatus.BAD_REQUEST.value()).errorTime(LocalDateTime.now()).build();
        }).collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundExceptionHandler(NotFoundException exception,
            WebRequest reqeust) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder().apiPath(reqeust.getDescription(false))
                .errorMessage(exception.getMessage()).errorCode(HttpStatus.NOT_FOUND.value())
                .errorTime(LocalDateTime.now()).build();

        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.NOT_FOUND);

    }

    // Helper method to extract the root cause message
    private String getRootCauseMessage(Throwable ex) {
        Throwable rootCause = ex;
        while (rootCause.getCause() != null && rootCause != rootCause.getCause()) {
            rootCause = rootCause.getCause();
        }
        return rootCause.getMessage();
    }
}
