package by.gurinovich.cryptologos.gpsolutionstechtask.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception exception) {
        return switch (exception){
            case EntityNotFoundException e -> createResponse(e, HttpStatus.NOT_FOUND, Map.of());
            case IllegalArgumentException e -> createResponse(e, HttpStatus.BAD_REQUEST, Map.of());
            case MethodArgumentNotValidException e -> handleMethodArgumentNotValidException(e);
            default -> createResponse(exception, HttpStatus.valueOf(500), Map.of());
        };
    }

    private ResponseEntity<Object> createResponse(Exception e, HttpStatus status, Map<String, Object> errors) {
        return ResponseEntity.status(status).body(new ExceptionResponse(errors, e.getMessage(), status));
    }

    private ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        record ErrorField(String field, Object message) {}
        var errors = e.getFieldErrors().stream()
                .map(error ->
                        new ErrorField(
                                error.getField(), error.getDefaultMessage()))
                .collect(toMap(ErrorField::field, ErrorField::message));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(errors, "Invalid arguments!", HttpStatus.BAD_REQUEST));
    }

}
