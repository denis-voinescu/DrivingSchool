package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.ExamMapper;
import org.example.drivingschool.model.EnrollmentEntity;
import org.example.drivingschool.model.ExamEntity;
import org.example.drivingschool.repository.EnrollmentRepository;
import org.example.drivingschool.repository.ExamRepository;
import org.openapitools.model.Exam;
import org.openapitools.model.ExamCreate;
import org.openapitools.model.ExamUpdate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExamService {

  private final ExamRepository examRepository;
  private final ExamMapper examMapper;
  private final EnrollmentRepository enrollmentRepository;

  public ExamService(
      ExamRepository examRepository,
      ExamMapper examMapper,
      EnrollmentRepository enrollmentRepository) {
    this.examRepository = examRepository;
    this.examMapper = examMapper;
    this.enrollmentRepository = enrollmentRepository;
  }

  @Transactional(readOnly = true)
  public List<Exam> list(LocalDate date) {
    return examRepository.findAll().stream().map(examMapper::toDto).toList();
  }

  @Transactional(readOnly = true)
  public Exam getById(Integer id) {
    if (id == null || id <= 0) {
      throw new InvalidIdException();
    }

    ExamEntity entity =
        examRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

    return examMapper.toDto(entity);
  }

  public Exam create(ExamCreate examCreate) {
    ExamEntity entity = examMapper.toEntity(examCreate);
    entity.setCreatedAt(Instant.now());
    ExamEntity saved = examRepository.save(entity);
    return examMapper.toDto(saved);
  }

  public Exam update(Integer id, ExamUpdate patch) {
    if (id == null || id <= 0) {
      throw new InvalidIdException();
    }

    ExamEntity entity =
        examRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

    examMapper.updateEntity(entity, patch);
    entity.setUpdatedAt(Instant.now());

    ExamEntity saved = examRepository.save(entity);
    return examMapper.toDto(saved);
  }

  public Exam createForEnrollment(Integer enrollmentId, ExamCreate examCreate) {
    if (enrollmentId == null || enrollmentId <= 0) {
      throw new InvalidIdException();
    }

    EnrollmentEntity enrollment =
        enrollmentRepository
            .findById(enrollmentId)
            .orElseThrow(() -> new ResourceNotFoundException(enrollmentId));

    ExamEntity entity = examMapper.toEntity(examCreate);
    entity.setEnrollment(enrollment);
    entity.setCreatedAt(Instant.now());

    ExamEntity saved = examRepository.save(entity);
    return examMapper.toDto(saved);
  }

  public List<Exam> listByEnrollment(Integer enrollmentId) {
    if (enrollmentId == null || enrollmentId <= 0) {
      throw new InvalidIdException();
    }

    EnrollmentEntity enrollment =
        enrollmentRepository
            .findById(enrollmentId)
            .orElseThrow(() -> new ResourceNotFoundException(enrollmentId));

    List<ExamEntity> entities = examRepository.findByEnrollment(enrollment);
    return examMapper.toDtoList(entities);
  }
}
