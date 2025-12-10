package org.example.drivingschool.service;

import org.example.drivingschool.mapper.InstructorMapper;
import org.example.drivingschool.repository.InstructorRepository;
import org.openapitools.model.Instructor;
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

}
