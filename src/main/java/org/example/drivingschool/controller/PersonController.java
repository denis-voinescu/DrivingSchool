package org.example.drivingschool.controller;

import org.example.drivingschool.service.PersonService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
}
