package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.StudentMapper;
import org.example.drivingschool.model.PersonEntity;
import org.example.drivingschool.model.StudentEntity;
import org.example.drivingschool.repository.PersonRepository;
import org.example.drivingschool.repository.StudentRepository;
import org.openapitools.model.Student;
import org.openapitools.model.StudentCreate;
import org.openapitools.model.StudentUpdate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PersonRepository personRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository,
                          PersonRepository personRepository,
                          StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.personRepository = personRepository;
        this.studentMapper = studentMapper;
    }

    @Transactional(readOnly = true)
    public List<Student> list() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Student getById(Integer id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        StudentEntity entity = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return studentMapper.toDto(entity);
    }

    public Student create(StudentCreate studentCreate) {

        PersonEntity person = personRepository.findById(studentCreate.getPersonId())
                .orElseThrow(() -> new ResourceNotFoundException(studentCreate.getPersonId()));

        StudentEntity entity = studentMapper.toEntity(studentCreate);
        entity.setPerson(person);
        entity.setCreatedAt(Instant.now());

        StudentEntity saved = studentRepository.save(entity);
        return studentMapper.toDto(saved);
    }

    public Student update(Integer id, StudentUpdate patch) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        StudentEntity entity = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        studentMapper.updateEntity(entity, patch);
        entity.setUpdatedAt(Instant.now());

        StudentEntity saved = studentRepository.save(entity);
        return studentMapper.toDto(saved);
    }
}
