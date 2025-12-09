package org.example.drivingschool.config;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.openapitools.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> handleIdFormat() {

        Error err = new Error();
        err.setCode("INVALID_ID_FORMAT");
        err.setMessage("ID must be a number");
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<Error> handleInvalidId(InvalidIdException ex) {

        Error err = new Error();
        err.setCode("INVALID_ID");
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleNotFound(ResourceNotFoundException ex) {

        Error err = new Error();
        err.setCode("RESOURCE_NOT_FOUND");
        err.setMessage(ex.getMessage());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
