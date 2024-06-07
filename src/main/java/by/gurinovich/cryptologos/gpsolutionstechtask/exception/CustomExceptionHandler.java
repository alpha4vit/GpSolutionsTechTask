package by.gurinovich.cryptologos.gpsolutionstechtask.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handle(Exception exception) {
        var response = switch (exception){
            case EntityNotFoundException e -> new ExceptionResponse(Map.of(), exception.getMessage(), HttpStatus.NOT_FOUND);
            case IllegalArgumentException e -> new ExceptionResponse(Map.of(), exception.getMessage(), HttpStatus.BAD_REQUEST);
            default -> new ExceptionResponse(Map.of(), exception.getMessage(), HttpStatus.valueOf(500));
        };
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


}
