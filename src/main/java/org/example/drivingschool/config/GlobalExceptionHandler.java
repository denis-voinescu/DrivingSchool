package org.example.drivingschool.config;

import org.example.drivingschool.exception.PersonNotFoundException;
import org.openapitools.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Error> handlePersonNotFound(PersonNotFoundException ex) {

        Error error = new Error();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setCode("PERSON_NOT_FOUND");
        error.setMessage(ex.getMessage());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
