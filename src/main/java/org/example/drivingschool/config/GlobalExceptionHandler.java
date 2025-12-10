package org.example.drivingschool.config;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.openapitools.model.Error;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleBadJson() {

        Error err = new Error();
        err.setCode("INVALID_JSON");
        err.setMessage("Malformed request body");
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGeneric() {

        Error err = new Error();
        err.setCode("INTERNAL_ERROR");
        err.setMessage("An unexpected error occurred");
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Error> handleDataIntegrity(DataIntegrityViolationException ex) {

        String c = ((ConstraintViolationException) ex.getCause()).getConstraintName();

        // Extract field name from constraint name pattern: person_pnc_key -> pnc
        String field = c.substring(c.indexOf('_') + 1, c.lastIndexOf('_'));

        Error err = new Error();
        err.setCode("CONFLICT");
        err.setMessage("Duplicate value for field: " + field);
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleRequestBodyFormat(MethodArgumentNotValidException ex) {

        List<String> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .distinct()
                .toList();

        String expression;
        switch (fields.size()) {
            case 1 -> expression = "field is";
            default -> expression = "fields are";
        }

        Error err = new Error();
        err.setCode("INVALID_REQUEST_BODY");
        err.setMessage("[" + String.join(", ", fields) +"] " + expression + " missing or invalid");
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.badRequest().body(err);
    }


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
