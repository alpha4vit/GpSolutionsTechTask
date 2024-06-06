package by.gurinovich.cryptologos.gpsolutionstechtask.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Objects;

public record ExceptionResponse(
    Map<String, Objects> errors,
    String message,
    HttpStatus status
) {
}
