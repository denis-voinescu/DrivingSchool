package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.InstructorMapper;
import org.example.drivingschool.model.InstructorEntity;
import org.example.drivingschool.model.PersonEntity;
import org.example.drivingschool.repository.InstructorRepository;
import org.openapitools.model.Instructor;
import org.openapitools.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    public InstructorService(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
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

        InstructorEntity entity = instructorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return instructorMapper.toDto(entity);
    }

}
