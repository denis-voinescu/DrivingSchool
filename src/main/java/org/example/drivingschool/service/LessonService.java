package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.LessonMapper;
import org.example.drivingschool.model.EnrollmentEntity;
import org.example.drivingschool.model.LessonEntity;
import org.example.drivingschool.repository.EnrollmentRepository;
import org.example.drivingschool.repository.LessonRepository;
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

    public LessonService(LessonRepository lessonRepository,
                         EnrollmentRepository enrollmentRepository,
                         LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.lessonMapper = lessonMapper;
    }

    @Transactional(readOnly = true)
    public List<Lesson> list(LocalDate date) {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Lesson getById(Integer id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        LessonEntity entity = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return lessonMapper.toDto(entity);
    }

    public Lesson createForEnrollment(Integer enrollmentId, LessonCreate lessonCreate) {
        if (enrollmentId == null || enrollmentId <= 0) {
            throw new InvalidIdException();
        }

        EnrollmentEntity enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException(enrollmentId));

        LessonEntity entity = lessonMapper.toEntity(lessonCreate);
        entity.setEnrollment(enrollment);
        entity.setCreatedAt(Instant.now());

        LessonEntity saved = lessonRepository.save(entity);
        return lessonMapper.toDto(saved);
    }

    public Lesson update(Integer id, LessonUpdate patch) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        LessonEntity entity = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        lessonMapper.updateEntity(entity, patch);
        entity.setUpdatedAt(Instant.now());

        LessonEntity saved = lessonRepository.save(entity);
        return lessonMapper.toDto(saved);
    }
}
