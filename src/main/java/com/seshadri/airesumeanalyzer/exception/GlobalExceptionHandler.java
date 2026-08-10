package com.seshadri.airesumeanalyzer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;




@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException(Exception ex) {
        ex.printStackTrace();

        Map<String, Object> response = new HashMap<>();

        response.put("status", 500);
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());

        return response;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {

    Map<String, Object> response = new HashMap<>();

    response.put("status", 400);
    response.put("error", "Validation Failed");

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );

    response.put("message", errors);

    return response;
}

    @ExceptionHandler(ResponseStatusException.class)
public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {

    Map<String, Object> response = new HashMap<>();

    response.put("status", ex.getStatusCode().value());
    response.put("error", ex.getStatusCode().toString());
    response.put("message", ex.getReason());

    return new ResponseEntity<>(response, ex.getStatusCode());
}
}