package org.example.drivingschool.service;

import org.example.drivingschool.exception.PersonNotFoundException;
import org.example.drivingschool.mapper.PersonMapper;
import org.example.drivingschool.model.PersonEntity;
import org.example.drivingschool.repository.PersonRepository;
import org.openapitools.model.Person;
import org.openapitools.model.PersonCreate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Transactional(readOnly = true)
    public List<Person> list() {
        return personRepository
                .findAll()
                .stream()
                .map(personMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Person getById(Integer id) {
        PersonEntity entity = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return personMapper.toDto(entity);
    }
}
