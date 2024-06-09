package by.gurinovich.cryptologos.gpsolutionstechtask.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record ExceptionResponse(
    Map<String, Object> errors,
    String message,
    HttpStatus status
) {
}
