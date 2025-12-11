package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.InstructorMapper;
import org.example.drivingschool.model.InstructorEntity;
import org.example.drivingschool.repository.InstructorRepository;
import org.example.drivingschool.repository.PersonRepository;
import org.openapitools.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class InstructorService {

  private final InstructorRepository instructorRepository;
  private final PersonRepository personRepository;
  private final InstructorMapper instructorMapper;

  public InstructorService(
      InstructorRepository instructorRepository,
      PersonRepository personRepository,
      InstructorMapper instructorMapper) {
    this.instructorRepository = instructorRepository;
    this.instructorMapper = instructorMapper;
    this.personRepository = personRepository;
  }

  @Transactional(readOnly = true)
  public List<Instructor> list() {

    return instructorRepository.findAll().stream().map(instructorMapper::toDto).toList();
  }

  @Transactional(readOnly = true)
  public Instructor getById(Integer id) {

    if (id == null || id <= 0) {
      throw new InvalidIdException();
    }

    InstructorEntity entity =
        instructorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

    return instructorMapper.toDto(entity);
  }

  public Instructor create(InstructorCreate instructorCreate) {

    InstructorEntity instructorEntity = instructorMapper.toEntity(instructorCreate);

    instructorEntity.setPerson(
        personRepository
            .findById(instructorCreate.getPersonId())
            .orElseThrow(() -> new ResourceNotFoundException(instructorCreate.getPersonId())));

    instructorEntity.setCreatedAt(Instant.now());
    InstructorEntity saved = instructorRepository.save(instructorEntity);

    return instructorMapper.toDto(saved);
  }

  public Instructor update(Integer id, InstructorUpdate patch) {

    if (id == null || id <= 0) {
      throw new InvalidIdException();
    }

    InstructorEntity entity =
        instructorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

    instructorMapper.updateEntity(entity, patch);
    entity.setUpdatedAt(Instant.now());

    InstructorEntity saved = instructorRepository.save(entity);
    return instructorMapper.toDto(saved);
  }
}
