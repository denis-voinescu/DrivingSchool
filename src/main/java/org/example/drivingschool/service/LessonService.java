package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.LessonMapper;
import org.example.drivingschool.model.EnrollmentEntity;
import org.example.drivingschool.model.InstructorEntity;
import org.example.drivingschool.model.LessonEntity;
import org.example.drivingschool.model.StudentEntity;
import org.example.drivingschool.repository.EnrollmentRepository;
import org.example.drivingschool.repository.InstructorRepository;
import org.example.drivingschool.repository.LessonRepository;
import org.example.drivingschool.repository.StudentRepository;
import org.openapitools.model.Lesson;
import org.openapitools.model.LessonCreate;
import org.openapitools.model.LessonUpdate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final LessonMapper lessonMapper;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    public LessonService(StudentRepository studentRepository ,LessonRepository lessonRepository, EnrollmentRepository enrollmentRepository, LessonMapper lessonMapper, InstructorRepository instructorRepository) {
        this.lessonRepository = lessonRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.lessonMapper = lessonMapper;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public List<Lesson> list(LocalDate date) {
        return lessonRepository.findAll().stream().map(lessonMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Lesson getById(Integer id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        LessonEntity entity = lessonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return lessonMapper.toDto(entity);
    }

    public Lesson createForEnrollment(Integer enrollmentId, LessonCreate lessonCreate) {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new InvalidIdException();
        }

        EnrollmentEntity enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new ResourceNotFoundException(enrollmentId));

        LessonEntity entity = lessonMapper.toEntity(lessonCreate);
        entity.setEnrollment(enrollment);
        entity.setCreatedAt(Instant.now());

        Integer nextCount = lessonRepository.countByEnrollment(enrollment) + 1;
        entity.setCount(nextCount);

        LessonEntity saved = lessonRepository.save(entity);
        return lessonMapper.toDto(saved);
    }

    public Lesson update(Integer id, LessonUpdate patch) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        LessonEntity entity = lessonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        lessonMapper.updateEntity(entity, patch);
        entity.setUpdatedAt(Instant.now());

        LessonEntity saved = lessonRepository.save(entity);
        return lessonMapper.toDto(saved);
    }

    public List<Lesson> listForInstructor(Integer instructorId, LocalDate date) {
        if (instructorId == null || instructorId <= 0) {
            throw new InvalidIdException();
        }

        InstructorEntity instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new ResourceNotFoundException(instructorId));

        List<LessonEntity> entities;

        if (date == null) {
            entities = lessonRepository.findByEnrollmentInstructor(instructor);
        } else {
            entities = lessonRepository.findByEnrollmentInstructorAndDate(instructor, date);
        }

        return entities.stream().map(lessonMapper::toDto).toList();
    }

    public List<Lesson> listForStudent(Integer studentId, Boolean completed) {
        if (studentId == null || studentId <= 0) {
            throw new InvalidIdException();
        }

        StudentEntity student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException(studentId));

        List<LessonEntity> entities;

        if (completed == null) {
            entities = lessonRepository.findByEnrollmentStudent(student);
        } else {
            entities = lessonRepository.findByEnrollmentStudentAndCompleted(student, completed);
        }

        return entities.stream().map(lessonMapper::toDto).toList();
    }

}
