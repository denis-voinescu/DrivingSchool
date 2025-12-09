package org.example.drivingschool.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Integer id) {
        super("Person with ID " + id + " not found.");
    }
}
