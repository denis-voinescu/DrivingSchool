package org.example.drivingschool.service;

import org.example.drivingschool.model.PersonEntity;
import org.example.drivingschool.repository.PersonRepository;
import org.openapitools.model.PersonCreate;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonEntity create(PersonCreate personCreate) {
        return null;
    }


}
