package org.example.drivingschool.controller;

import jakarta.validation.Valid;
import org.example.drivingschool.service.PersonService;
import org.openapitools.api.PersonsApi;
import org.openapitools.model.Person;
import org.openapitools.model.PersonCreate;
import org.openapitools.model.PersonUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController implements PersonsApi {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonCreate personCreate) {
        return ResponseEntity.ok(personService.create(personCreate));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        return ResponseEntity.ok(personService.getById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Person>> listPersons() {
        return ResponseEntity.ok(personService.list());
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, PersonUpdate personUpdate) {
        return ResponseEntity.ok(personService.update(id, personUpdate));
    }
}
